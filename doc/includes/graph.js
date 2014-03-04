/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
Object_create = Object.create || function (o) {
	var F = function() {};
	F.prototype = o;
	return new F();
};
/* Node */
GraphNode = function(id, content){
	this.typ = "node";
	this.id = id;
	this.x = 0;
	this.y = 0;
	this.width=0;
	this.height=0;
	this.edges = [];
	this.attributes = [];
	this.methods = [];
	this.content = content;
	this.hide = function() {
		this.hidden = true;
		for(i in this.edges)
			(this.edges[i].source.id == id || this.edges[i].target == id) && this.edges[i].hide && this.edges[i].hide();
	};
	this.show = function() {
		this.hidden = false;
		for(i in this.edges)
			(this.edges[i].source.id == id || this.edges[i].target == id) && this.edges[i].show && this.edges[i].show();
	};
}

/* Edge */
Edge = function() {};
Edge.prototype = {
	hide: function() {
		this.connection.fg.hide();
		this.connection.bg && this.bg.connection.hide();
	}
};
Generalisation = function() {};
Generalisation.prototype = Object_create(Edge.prototype);
Generalisation.prototype.constructor = Generalisation;

/* Graph */
Graph = function(json, canvasid) {
	this.nodeCount=0;
	this.nodes = {};
	this.edges = [];
	this.canvasid = canvasid;

	for (var i in json.value.nodes) {
		var node = json.value.nodes[i];
		node.x = 0;
		node.y = 0;
		node.width=0;
		node.height=0;
		node.edges = [];
		this.insertNode(node);
	}
	var list = json.value.edges;
	for (var i in list){
		var edge;
		if(list[i].typ=="generalisation"){
			edge = new Generalisation();
		}else{
			edge = new Edge();
		}
		var fromnode = this.getNode(list[i].source);
		edge.source = fromnode;
		edge.sourceinfo = list[i].sourceinfo;
		edge.targetinfo = list[i].targetinfo;
		fromnode.edges.push(edge);
		
		var toNode =this.getNode(list[i].target);
		edge.target = toNode;
		toNode.edges.push(edge);
		
		this.edges.push(edge);
	}
	if(!this.canvasid){
		this.canvasid = "canvas";
	}
	if(!document.getElementById(this.canvasid)){
		document.write("<div id=\""+this.canvasid+"\" class=\"Board\">");
	}
	this.board = document.getElementById(this.canvasid);
	for (var i in this.nodes) {
		var node = this.nodes[i];
		var html = this.getHTMLNode(node, i);
		this.board.appendChild(html);
		node.width=html.offsetWidth;
		node.height=html.offsetHeight;
		this.board.removeChild(html);
	}
};
Graph.prototype = {
/* 
 add a node
 @id		the node's ID (string or number)
 @content	(optional, dictionary) can contain any information that is
			being interpreted by the layout algorithm or the graph
			representation
*/
	addNode: function(id, content) {
		/* testing if node is already existing in the graph */
		if(this.nodes[id] == undefined) {
			this.nodes[id] = new GraphNode(id, content);
			this.nodes[id].parent = this;
			this.nodeCount++;
		}
		return this.nodes[id];
	},
	getNode: function(id) {
		if(this.nodes[id] == undefined) {
			this.nodes[id] = new GraphNode(id, "");
			this.nodes[id].parent = this;
			this.nodeCount++;
		}
		return this.nodes[id];
	},
	insertNode: function(node) {
		/* testing if node is already existing in the graph */
		if(this.nodes[node.id] == undefined) {
			this.nodes[node.id] = node;
			node.parent = this;
			this.nodeCount++;
		}
		return this.nodes[node.id];
	},
	addEdge: function(source, target) {
		var edge = new Edge();
		edge.source = this.addNode(source);
		edge.target = this.addNode(target);
		edge.source.edges.push(edge);
		this.edges.push(edge);
		// NOTE: Even directed edges are added to both nodes.
		edge.target.edges.push(edge);
	},
	removeNode: function(id) {
		delete this.nodes[id];
		for(var i = 0; i < this.edges.length; i++) {
			if (this.edges[i].source.id == id || this.edges[i].target.id == id) {
				this.edges.splice(i, 1);
				i--;
			}
		}
	},
	drawLines: function(){
		var list = this.edges;
		for(var i in list) {
			var edge = list[i];
			if(edge.htmlElement){
				while(edge.htmlElement.length>0){
					this.board.removeChild(edge.htmlElement.pop());
				}
			}
		}
		for(var i in list) {
			new RectLine().draw(this.board, list[i]);
		}
	},
	drawGraph : function(width, height){
		var list = this.nodes;
		this.board.style.width = width;
		this.board.style.height = height;
		for(i in list) {
			this.board.appendChild( this.getHTMLNode(list[i], i) );
		}
		this.drawLines();
		this.infoBox = document.createElement("div");
		this.infoBox.id = "infobox";
		this.infoBox.style.left = this.board.offsetWidth-200;
		this.infoBox.style.top = this.board.offsetHeight-42;
		this.infoBox.style.width = 200;
		this.infoBox.style.height = 42;
		this.infoBox.className = "infoBox";
		this.infoBox.style.display="none";
		this.infoBox.fader=false;
		this.infoBox.onmouseout = function (evt) {fadeout();};
		this.infoBox.onmouseover= function (evt) {fadein();};
		this.board.appendChild(this.infoBox);
	},
	showInfoText : function(text){
		this.infoBox.fader=false;
		this.infoBox.innerHTML = text;
		this.infoBox.style.opacity = 100;
		this.infoBox.style.MozOpacity = 100;
		this.infoBox.style.KhtmlOpacity = 100;
		this.infoBox.style.display="";
	},
	getHTMLNode : function(node, i){
		var htmlElement = document.createElement("div");
		htmlElement.className="classElement";
		htmlElement.style.left=node.x+"px";
		htmlElement.style.left=node.x+"px";
		htmlElement.style.top=node.y+"px";
		htmlElement.style.zIndex=5000;
		htmlElement.addEventListener("mousedown", startDrag, false);
		htmlElement.addEventListener("mouseover", showinfo, false);
		htmlElement.addEventListener("mouseout", fadeout, false);
		//var text="<div class='classElement' style='left:"+node.x+"px;top:"+node.y+"px;z-index:5000;' onmousedown='startDrag(this);' onmouseover='showinfo(this);' onmouseout='fadeout();'>";
		var text= "<table border=0><tr><th>"+ node.id+"</th></tr>";
		if(node.attributes){
			var first=true;
			for(var a in node.attributes){
				var attribute = node.attributes[a];
				if(!first){
					text += "<tr><td class='attributes'>"+attribute+"</td></tr>";
				}else{
					text += "<tr><td class='attributes first'>"+attribute+"</td></tr>";
					first=false;
				}
			}
		}
		if(node.methods){
			var first=true;
			for(var m in node.methods){
				var method = node.methods[m];
				if(!first){
					text += "<tr><td class='methods'>"+method+"</td></tr>";
				}else{
					text += "<tr><td class='methods first''>"+method+"</td></tr>";
					first=false;
				}
			}
		}
		text = text + "</table>";
		//htmlElement.innerHTML = text + "</div>";
		htmlElement.innerHTML = text;
		htmlElement.node = node;
		node.htmlElement = htmlElement;
		return htmlElement;
	}
};

GraphLayout = function (){};

Pos = function(x, y, id) {
	this.x = x;
	this.y = y;
	if(id){this.id = id;}
}

//	########################################### DRAG AND DROP #########################################################
var objDrag = null;		// Element, über dem Maus bewegt wurde
var mouse = new Pos(0,0);
var offset= new Pos(0,0);

// Browserweiche
IE = document.all&&!window.opera;
DOM = document.getElementById&&!IE;

// Initialisierungs-Funktion
function init(){
	// Initialisierung der Überwachung der Events
	document.onmousemove = doDrag;	// Bei Mausbewegung die Fkt. doDrag aufrufen
	document.onmouseup = stopDrag;	// Bei Loslassen der Maustaste die Fkt. stopDrag aufrufen
}
function showinfo(objElem){
	var node=getNode(objElem);
	if(node){
		var x = Math.round( objElem.style.left.substring(0,objElem.style.left.length-2) * 100)/100;
		var y = Math.round( objElem.style.top.substring(0,objElem.style.top.length-2) * 100)/100;
		node.parent.showInfoText("Box-Position: " + x + ":" + y);
	}
}
function startDrag(event) {
	objDrag = event.currentTarget;
	offset.x = mouse.x - objDrag.offsetLeft;
	offset.y = mouse.y - objDrag.offsetTop;
}
// Wird ausgeführt, wenn die Maus bewegt wird
function doDrag(event) {
	mouse.x = (IE) ? window.event.clientX : event.pageX;
	mouse.y = (IE) ? window.event.clientY : event.pageY;

	// Wurde die Maus über einem Element gedrück, erfolgt eine Bewegung
	if (objDrag != null) {
		// Element neue Koordinaten zuweisen
		objDrag.style.left = (mouse.x - offset.x) + "px";
		objDrag.style.top = (mouse.y - offset.y) + "px";

		// Position in Statusleiste ausgeben
		window.status = "Box-Position: " + objDrag.style.left + ", " + objDrag.style.top;
		var model = savePosition(objDrag);
		if(model){
			model.parent.drawLines();
		}
	}
}
function getNode(objElement){
	var obj=objElement;
	while(obj&&!obj.node){
		if(!obj.node){
			obj=obj.parentNode;
		}else{
			break;
		}
	}
	if(obj&&obj.node){
		return obj.node;
	}
	return null;
}
function savePosition(objElem){
	var node=getNode(objElem);
	if(node){
		node.x = objElem.offsetLeft;
		node.y = objElem.offsetTop;
		return node;
	}
	return null;
}
function fadein(){
	var item=document.getElementById("infobox");
	if(item){item.fader=false;}
}
function fadeout(){
	var item=document.getElementById("infobox");
	if(item&&!item.fader){
		item.fader=true;
		setTimeout("fadeOutTimer('infobox', 100)", 2000);
	}
}
// Wird ausgeführt, wenn die Maustaste losgelassen wird
function stopDrag(ereignis) {
	// Objekt löschen -> beim Bewegen der Maus wird Element nicht mehr verschoben
	var model = savePosition(objDrag);
	objDrag = null;
	if(model){
		model.parent.drawLines();
	}
}

function fadeOutTimer(id, value) {
	var item=document.getElementById(id);
	if(item && item.fader){
		if(value>0.00){
			item.style.opacity = (value / 100);
			item.style.MozOpacity = (value / 100);
			item.style.KhtmlOpacity = (value / 100);
			value=value-5;
			setTimeout("fadeOutTimer('"+id+"',"+value+");", 20);
		}else{
			item.style.display="none";
		}
	}
}
