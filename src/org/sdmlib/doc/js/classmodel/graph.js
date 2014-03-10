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
Object_create = Object.create || function (o) { var F = function() {};F.prototype = o; return new F();};
/* Pos */
Pos = function(x, y, id) {this.x = x; this.y = y; if(id){this.id = id;} }
/* Item */
Item = function(pos, size) { this.pos = pos; this.size = size; this.center=new Pos(0,0); }

Line = function(source, target, style) {this.source = source; this.target = target; this.style = style;}
Line.Format={SOLID:"SOLID", DOTTED:"DOTTED"};
/* Options */
Options = function(){
	this.raster = false;
	this.display = "svg";
	this.bar = true;
	this.canvasid = "canvas";
}

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
}

/* Graph */
Graph = function(json, options) {
	this.nodeCount=0;
	this.nodes = {};
	this.edges = [];
	this.typ = json.typ;
	if(options){
		this.options = options;
	}else if(json.options){
		this.options = json.options;
	}else{
		this.options = new Options();
	}
	if((""+this.options.display).toLowerCase()=="html"){
		this.drawer = new HTMLDrawer();
	}else{
		this.drawer = new SVGDrawer();
		this.options.display = "svg";
	}
	this.canvasid = this.options.canvasid ||  "canvas";
	this.minSize = new Pos(0, 0);

	for (var i in json.value.nodes) {
		var node = json.value.nodes[i];
		node.x = node.x || 0;
		node.y = node.y || 0;
		node.width = node.width || 0;
		node.height = node.height || 0;
		node.edges = [];
		node.RIGHT = node.LEFT = node.UP = node.DOWN=0;
		this.insertNode(node);
	}
	for (var i in json.value.edges){
		var e = json.value.edges[i];
		var edge;
		if(e.typ.toLowerCase()=="generalisation"){
			edge = new Generalisation();
		}else if(e.typ.toLowerCase()=="implements"){
			edge = new Implements();			
		}else{
			edge = new Edge();
		}
		edge.source = this.getNode(e.source);
		edge.sourceproperty = e.sourceproperty;
		edge.targetproperty = e.targetproperty;
		edge.source.edges.push(edge);

		edge.target = this.getNode(e.target);
		edge.target.edges.push(edge);
		
		this.edges.push(edge);
	}
	if(!document.getElementById(this.canvasid)){
		document.write("<div id=\""+this.canvasid+"\" />");
	}
	this.root = document.getElementById(this.canvasid);
	
	if(this.options.bar){
		document.write("<div id='"+this.canvasid+"option' class=\"Options\" />");
		//<button>HTML</button><button>SVG</button><button>SVG CODE</button></div>");
		this.optionbar=document.getElementById(this.canvasid+"option");
		this.optionbar.appendChild(this.getButton("HTML"));
		this.optionbar.appendChild(this.getButton("SVG"));
		this.optionbar.appendChild(this.getButton("SVG CODE"));
		this.optionbar.appendChild(document.createElement("br"));
	}
	this.initGraph();
};
Graph.prototype.initGraph = function(){
	if(this.board){
		this.clearLines();
		for(i in this.nodes) {
			if(this.nodes[i].htmlNode){
				this.board.removeChild(this.nodes[i].htmlNode);
				this.nodes[i].htmlNode = null;
			}
		}
		this.root.removeChild(this.board);
	}
	if(this.infoBox){
		this.board.removeChild(this.infoBox);
		this.infoBox=null;
	}

	this.board = this.drawer.createContainer(this.canvasid);
	this.root.appendChild(this.board);
	for (var i in this.nodes) {
		var node = this.nodes[i];
		var html = this.drawer.getHTMLNode(node, i, this.typ, this.board);
		this.board.appendChild(html);
		var rect = html.getBoundingClientRect();
		node.width=rect.width;
		node.height=rect.height;
		this.board.removeChild(html);
	}
	for (var i in this.edges) {
		var edge = this.edges[i];
		edge.sourceinfo = new Item(new Pos(0,0), new Pos(0,0) );
		edge.targetinfo = new Item(new Pos(0,0), new Pos(0,0) );
		if(edge.sourceproperty){
			var html = this.drawer.createInfo(0, 0, edge.sourceproperty);
			if(html){
				this.board.appendChild(html);
				edge.sourceinfo.size = new Pos(rect.width, rect.height);
				this.board.removeChild(html);
			}
		}
		if(edge.targetproperty){
			var html = this.drawer.createInfo(0, 0, edge.targetproperty);
			if(html){
				this.board.appendChild(html);
				edge.targetinfo.size = new Pos(rect.width, rect.height);
				this.board.removeChild(html);
			}
		}
	}
}
Graph.prototype.getButton = function(label){
	var button = document.createElement("button");
	button.innerHTML = label;
	button.graph = this;
	button.addEventListener("click", optionButton, false);
	return button;
}

/* 
 add a node
 @id		the node's ID (string or number)
 @content	(optional, dictionary) can contain any information that is
			being interpreted by the layout algorithm or the graph
			representation
*/
Graph.prototype.addNode = function(id, content) {
	/* testing if node is already existing in the graph */
	if(this.nodes[id] == undefined) {
		this.nodes[id] = new GraphNode(id, content);
		this.nodes[id].parent = this;
		this.nodeCount++;
	}
	return this.nodes[id];
};
Graph.prototype.getNode = function(id) {
	if(this.nodes[id] == undefined) {
		this.nodes[id] = new GraphNode(id, "");
		this.nodes[id].parent = this;
		this.nodeCount++;
	}
	return this.nodes[id];
};
Graph.prototype.insertNode = function(node) {
	/* testing if node is already existing in the graph */
	if(this.nodes[node.id] == undefined) {
		this.nodes[node.id] = node;
		node.parent = this;
		this.nodeCount++;
	}
	return this.nodes[node.id];
};
Graph.prototype.addEdge = function(source, target) {
	var edge = new Edge();
	edge.source = this.addNode(source);
	edge.target = this.addNode(target);
	edge.source.edges.push(edge);
	this.edges.push(edge);
	// NOTE: Even directed edges are added to both nodes.
	edge.target.edges.push(edge);
};
Graph.prototype.removeNode = function(id) {
	if(this.nodes[id].htmlNode){
		this.board.removeChild(this.nodes[id].htmlNode);
	}
	delete(this.nodes[id]);
	
	for(var i = 0; i < this.edges.length; i++) {
		if (this.edges[i].source.id == id || this.edges[i].target.id == id) {
			this.edges.splice(i, 1);
			i--;
		}
	}
};
Graph.prototype.drawLines = function(){
	this.clearLines();
	var ownAssoc = []
	for(var i in this.edges) {
		if(!this.edges[i].calculate(this.board, this.drawer)){
			ownAssoc.push(this.edges[i]);
		}
	}
	for(var i in this.edges) {
		this.edges[i].draw(this.board, this.drawer);
	}

};
Graph.prototype.clearLines = function(){
	for(var i in this.edges) {
		var edge = this.edges[i];
		if(edge.htmlElement){
			while(edge.htmlElement.length>0){
				this.board.removeChild(edge.htmlElement.pop());
			}
		}
	}
	for(var i in this.nodes) {
		this.nodes[i].RIGHT = this.nodes[i].LEFT = this.nodes[i].UP = this.nodes[i].DOWN=0;
	}
};
Graph.prototype.resize = function(){
	var maxx=0, maxy=0;
	for (var i in this.nodes) {
		var node = this.nodes[i];
		
		this.NodeMoveRaster(node);
		
		maxx=Math.max(maxx,node.x+node.width);
		maxy=Math.max(maxy,node.y+node.height);
	}
	this.board.style.width = Math.max(this.minSize.x, maxx+20);
	this.board.style.height = Math.max(this.minSize.y, maxy+50);
	if(this.drawer.showInfoBox()){
		this.infoBox.style.left = this.board.offsetWidth-200;
		this.infoBox.style.top = this.board.offsetHeight-42;
	}
	if(this.drawer.isShowRaster() && this.options.raster){
		this.drawRaster();
	}
};
Graph.prototype.drawRaster = function(){
	while(this.board.rasterElements.length>0){
		this.board.removeChild(this.board.rasterElements.pop());
	}
	var width = this.board.style.width.replace("px","");
	var height = this.board.style.height.replace("px","");
	for(var i=10;i<width;i+=10){
		var item = document.createElement("div");
		item.className="lineRaster";
		item.style.height = height + "px";
		item.style.left = i+"px";
		this.board.rasterElements.push(item);
		this.board.appendChild(item);
	}
	for(var i=10;i<height;i+=10){
		var item = document.createElement("div");
		item.className="lineRaster";
		item.style.width = width + "px";
		item.style.top = i+"px";
		this.board.rasterElements.push(item);
		this.board.appendChild(item);
	}	
};
Graph.prototype.drawGraph = function(width, height){
	this.minSize = new Pos(width, height);
	if(this.drawer.showInfoBox()){
		this.infoBox = document.createElement("div");
	}
	this.resize();

	for(i in this.nodes) {
		this.nodes[i].htmlNode = this.drawer.getHTMLNode(this.nodes[i], i, this.typ, this.board);
		this.board.appendChild( this.nodes[i].htmlNode );
	}
	this.drawLines();
	
	if(this.drawer.showInfoBox()){
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
	}
};
Graph.prototype.showInfoText = function(text){
	this.infoBox.fader=false;
	this.infoBox.innerHTML = text;
	this.infoBox.style.opacity = 100;
	this.infoBox.style.MozOpacity = 100;
	this.infoBox.style.KhtmlOpacity = 100;
	this.infoBox.style.display="";
};
Graph.prototype.NodeMoveRaster = function(node){
	if(this.options.raster){
		node.x = parseInt(node.x / 10) * 10;
		node.y = parseInt(node.y / 10) * 10;
		if(node.htmlNode){
			node.htmlNode.style.left = node.x+"px";
			node.htmlNode.style.top = node.y+"px";
		}
	}
}
/* GraphLayout with Default Dagre */
GraphLayout = function (){};
GraphLayout.Dagre = function(graph) {
    this.graph = graph;
    this.layouting();
};
GraphLayout.Dagre.prototype = {
    layouting: function() {
		this.g = new dagre.Digraph();
		for (var i in this.graph.nodes) {
			var node = this.graph.nodes[i];
			this.g.addNode(node.id, {"label": node.id, "width":node.width+10, "height":node.height+20});
		}
		for (var i in this.graph.edges) {
			var edges = this.graph.edges[i];
			edges.id = i;
			this.g.addEdge(i, edges.source.id, edges.target.id);
		}
    },
	layout: function(width, height) {
		var layout = dagre.layout().run(this.g);
		// Set the layouting back
		for (var i in this.graph.nodes) {
			var node = this.graph.nodes[i];
			var layoutNode = layout._nodes[node.id];
			node.x = layoutNode.value.x;
			node.y = layoutNode.value.y;
		}
		this.graph.drawGraph(width, height);
    }
};

//					######################################################### DRAG AND DROP #########################################################
var objDrag = null;
var mouse = new Pos(0,0);
var offset= new Pos(0,0);

IE = document.all&&!window.opera;
DOM = document.getElementById&&!IE;

function init(){
	document.onmousemove = doDrag;
	document.onmouseup = stopDrag;
}
function showinfo(event){
	var objElem = event.currentTarget;
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
function doDrag(event) {
	mouse.x = (IE) ? window.event.clientX : event.pageX;
	mouse.y = (IE) ? window.event.clientY : event.pageY;

	if (objDrag != null) {
		objDrag.style.left = (mouse.x - offset.x) + "px";
		objDrag.style.top = (mouse.y - offset.y) + "px";

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
function stopDrag(ereignis) {
	var model = savePosition(objDrag);
	objDrag = null;
	if(model){
		model.parent.drawLines();
		model.parent.resize();
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

function optionButton(event){
	var btn = event.currentTarget;
	if(btn.innerHTML=="HTML"){
		btn.graph.drawer = new HTMLDrawer();
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
	}else if(btn.innerHTML=="SVG"){
		btn.graph.drawer = new SVGDrawer();
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
	}else if(btn.innerHTML=="SVG CODE"){
		btn.graph.drawer = new SVGDrawer();
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
		window.open("data:image/svg+xml," + escape(btn.graph.board.outerHTML));
	}
}

String.prototype.endsWith = function(suffix) {return this.indexOf(suffix, this.length - suffix.length) !== -1;};
function isIE () {
	var myNav = navigator.userAgent.toLowerCase();
	return (myNav.indexOf('msie') != -1 || myNav.endsWith('like gecko') );
}

//					######################################################### LINES #########################################################
Edge = function() {this.init();}
Edge.prototype.init = function(){
	this.path = [];
	this.source=null;
	this.target=null;
	this.start = null;
	this.end = null;
	this.htmlElement = new Array();
	this.m = 0;
	this.n = 0;
	this.lineStyle = Line.Format.SOLID;
};
Edge.Layout ={ DIAG : 1, RECT : 0 };
Edge.Position={UP:"UP", LEFT:"LEFT", RIGHT:"RIGHT", DOWN:"DOWN"};

Edge.prototype.addElement = function(board, list, element){
	if(element){
		list.push(element);board.appendChild(element);
	}
};
Edge.prototype.calculate = function(board, drawer){
	this.source.center = new Pos(this.source.x + (this.source.width / 2), this.source.y + (this.source.height / 2));
	this.target.center = new Pos(this.target.x + (this.target.width / 2), this.target.y + (this.target.height / 2));
	return this.calcCenterLine();
}
Edge.prototype.draw = function(board, drawer){
	for(var i=0;i<this.path.length;i++){
		this.addElement(board, this.htmlElement, drawer.createLine(this.path[i].source.x, this.path[i].source.y, this.path[i].target.x, this.path[i].target.y, this.path[i].style));
	}
	if(this.sourceproperty){
		this.addElement(board, this.htmlElement, drawer.createInfo(this.sourceinfo.pos.x, this.sourceinfo.pos.y, this.sourceproperty));
	}
	if(this.targetproperty){
		this.addElement(board, this.htmlElement, drawer.createInfo(this.targetinfo.pos.x, this.targetinfo.pos.y, this.targetproperty));
	}
};
Edge.prototype.endPos = function(){
	return this.path[this.path.length-1];
}
Edge.prototype.calcCenterLine = function(){
	var divisor = (this.target.center.x - this.source.center.x);
	var sourcePos,targetPos;
	this.path = new Array();
	if(divisor==0){
		if(this.source==this.target){
			// OwnAssoc
			return false;
		}
		// Must be UP_DOWN or DOWN_UP
		if(this.source.center.y<this.target.center.y){
			// UP_DOWN
			sourcePos = new Pos(this.source.center.x ,  (this.source.y+this.source.height), Edge.Position.DOWN);
			targetPos = new Pos(this.target.center.x ,  (this.target.y), Edge.Position.UP);
			this.sourceinfo.pos = new Pos(sourcePos.x + 5, sourcePos.y + 5);
			this.targetinfo.pos = new Pos(targetPos.x + 5, targetPos.y - 5 - this.targetinfo.size.y);
		}else{
			sourcePos = new Pos(this.source.center.x ,  (this.source.pos.y), Edge.Position.UP);
			targetPos = new Pos(this.target.center.x ,  (this.target.pos.y+this.target.size.y), Edge.Position.DOWN);
			this.sourceinfo.pos = new Pos(sourcePos.x + 5, sourcePos.y - 5 - this.sourceinfo.size.y);
			this.targetinfo.pos = new Pos(targetPos.x + 5, targetPos.y + 5);
		}
	}else{
		this.m = (this.target.center.y - this.source.center.y) / divisor;
		this.n = this.source.center.y - (this.source.center.x * this.m);
		sourcePos = this.getPosition(this.m,this.n, this.source, this.target.center);
		targetPos = this.getPosition(this.m,this.n, this.target, sourcePos);
		if(sourcePos&&targetPos){
			pos = sourcePos.id+ "_" + targetPos.id;
			this.calcInfoPos( sourcePos, this.source, this.sourceinfo);
			this.calcInfoPos( targetPos, this.target, this.targetinfo);
		}
	}
	if(sourcePos&&targetPos){
		this.addEdgeToNode(this.source, sourcePos.id);
		this.addEdgeToNode(this.target, targetPos.id);
		this.path.push ( new Line(sourcePos, targetPos, this.lineStyle));
	}
	return true;
};
Edge.prototype.addEdgeToNode=function(node, pos){
	if(pos=="UP"){
		node.UP+=1;
	}else if(pos=="DOWN"){
		node.DOWN+=1;
	}else if(pos=="RIGHT"){
		node.RIGHT+=1;
	}else if(pos=="LEFT"){
		node.LEFT+=1;
	}
};

Edge.prototype.calcInfoPos = function( linePos, item, info){
	var newY = linePos.y;
	var newX = linePos.x;
	var yoffset =0;
	if(linePos.id==Edge.Position.UP){
		newY = newY - info.size.y -5;
		newX = (newY-this.n) / this.m + 10;
	}else if(linePos.id==Edge.Position.DOWN){
		newY = newY + 5;
		newX = (newY-this.n) / this.m + 10;
	}
	info.pos = new Pos(newX, newY);
};

Edge.prototype.getPosition= function(m , n, entity, refCenter){
	var x,y;
	var pos=new Array();
	var distance=new Array();
	x = entity.x+entity.width;
	y = m*x+n;
	if(y>=entity.y && y<=(entity.y+entity.height)){
		pos.push(new Pos(x , y, Edge.Position.RIGHT));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	y = entity.y;
	x = (y-n)/m;
	if(x>=entity.x && x<=(entity.x+entity.width)){
		pos.push(new Pos(x , y, Edge.Position.UP));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	x = entity.x;
	y = m*x+n;
	if(y>=entity.y && y<=(entity.y+entity.height)){
		pos.push(new Pos(x , y, Edge.Position.LEFT));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	y = entity.y+entity.height;
	x = (y-n)/m;
	if(x>=entity.x && x<=(entity.x+entity.width)){
		pos.push(new Pos(x , y, Edge.Position.DOWN));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	var min=999999999;
	var position;
	for(var i in pos){
		if(distance[i]<min){
			 min = distance[i];
			 position = pos[i];
		}
	}
	return position;
};
Generalisation = function() { this.init();};
Generalisation.prototype = Object_create(Edge.prototype);
Generalisation.prototype.constructor = Generalisation;
Generalisation.prototype.initEdge = Generalisation.prototype.init;
Generalisation.prototype.init =function(){ this.initEdge(); this.size=16;this.angle = 50; }
Generalisation.prototype.calculateEdge = Generalisation.prototype.calculate;
Generalisation.prototype.calculate = function(board, drawer){
	if(!this.calculateEdge(board, drawer)){
		return false;
	}

	var startArrow  = this.endPos().source;
	var targetArrow  = this.endPos().target;
	// calculate the angle of the line
	var lineangle=Math.atan2(targetArrow.y-startArrow.y,targetArrow.x-startArrow.x);
	// h is the line length of a side of the arrow head
	var h=Math.abs(this.size/Math.cos(this.angle));
	var angle1=lineangle+Math.PI+this.angle;
	this.top = new Pos(targetArrow.x+Math.cos(angle1)*h, targetArrow.y+Math.sin(angle1)*h);
	var angle2=lineangle+Math.PI-this.angle;
	this.bot = new Pos(targetArrow.x+Math.cos(angle2)*h, targetArrow.y+Math.sin(angle2)*h);
	var pos = new Pos((this.top.x + this.bot.x) / 2, (this.top.y + this.bot.y) / 2);
	this.end = this.path[this.path.length-1].target;
	this.endPos().target = pos;
	return true;
};
Generalisation.prototype.drawSuper = Generalisation.prototype.draw;
Generalisation.prototype.draw = function(board, drawer){
	this.drawSuper(board, drawer);
	this.addElement(board, this.htmlElement, drawer.createLine(this.top.x, this.top.y, this.end.x, this.end.y, this.lineStyle));
	this.addElement(board, this.htmlElement, drawer.createLine(this.bot.x, this.bot.y, this.end.x, this.end.y, this.lineStyle));
	this.addElement(board, this.htmlElement, drawer.createLine(this.top.x, this.top.y, this.bot.x, this.bot.y, this.lineStyle));
};

Implements  = function() { this.init(); }
Implements.prototype = Object_create(Generalisation.prototype);
Implements.prototype.constructor = Implements;
Implements.prototype.initGeneralisation = Implements.prototype.init;
Implements.prototype.init =function(){
	this.initGeneralisation();
	this.lineStyle = Line.Format.DOTTED;
}
