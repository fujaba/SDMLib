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
IE = document.all&&!window.opera;
DOM = document.getElementById&&!IE;

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
	this.canvasid = null;
	this.rootElement = null;
	this.parent = null;
	this.fontsize=12;
	this.rank = "LR";	// Dagre TB, LR
	this.nodeSep = 20;
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
	this.layouter = new DagreLayout();
	this.options = this.merge(new Options(), json.options, options);
	this.parent = this.options.parent;
	this.loader = new Loader(this);
	if((""+this.options.display).toLowerCase()=="html"){
		this.drawer = new HTMLDrawer();
		this.loader.init(true);
	}else{
		this.drawer = new SVGDrawer();
		this.options.display = "svg";
	}
	this.minSize = new Pos(0, 0);

	for (var i in json.value.nodes) {
		var node = json.value.nodes[i];
		node.x = node.x || 0;
		node.y = node.y || 0;
		node.startWidth = node.width;
		node.startHeight = node.height;
		node.width = node.width || 0;
		node.height = node.height || 0;
		node.edges = [];
		node.RIGHT = node.LEFT = node.UP = node.DOWN=0;
		this.insertNode(node, i);
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
	if(this.options.canvasid){
		this.root = document.getElementById(this.options.canvasid);
	}else if(this.options.rootElement){
		this.root = this.options.rootElement;
	}
	if(!this.root){
		this.root = document.createElement("div");
		if(this.options.canvasid){
			this.root.id = this.options.canvasid;
		}
		document.body.appendChild(this.root);
	}
	if(this.options.bar){
		this.optionbar = document.createElement("div");
		this.optionbar.className = "Options";
		this.root.appendChild(this.optionbar);
		//<button>HTML</button><button>SVG</button><button>SVG CODE</button></div>");
		this.optionbar.appendChild(this.getButton("HTML"));
		this.optionbar.appendChild(this.getButton("SVG"));
		this.optionbar.appendChild(this.getButton("SVG-Export"));
		this.optionbar.appendChild(this.getButton("PNG-Export"));
		this.optionbar.appendChild(document.createElement("br"));
	}
	this.initGraph();
};

Graph.prototype.merge = function(ref, sourceA, sourceB){
	if(sourceA){
		for(var i in sourceA){
			ref[i] = sourceA[i];
		}
	}
	if(sourceB){
		for(var i in sourceB){
			ref[i] = sourceB[i];
		}
	}
	return ref;
}

Graph.prototype.initGraph = function(){
	this.clearBoard();
	this.board = this.drawer.createContainer(this);
	this.initDragAndDrop();
	this.root.appendChild(this.board);
	
	for (var i in this.nodes) {
		var node = this.nodes[i];
		var html = this.drawer.getHTMLNode(node, true);
		if(html){
			var pos = this.getDimension(html);
			if(!node.startWidth){
				node.width=pos.x;
			}
			if(!node.startHeight){
				node.height=pos.y;
			}
		}
	}

	for (var i in this.edges) {
		var edge = this.edges[i];
		edge.sourceinfo = new Item(new Pos(0,0), new Pos(0,0) );
		edge.targetinfo = new Item(new Pos(0,0), new Pos(0,0) );
		if(edge.sourceproperty){
			var html = this.drawer.createInfo(0, 0, edge.sourceproperty, true);
			if(html){
				edge.sourceinfo.size = this.getDimension(html);
			}
		}
		if(edge.targetproperty){
			var html = this.drawer.createInfo(0, 0, edge.targetproperty, true);
			if(html){
				edge.targetinfo.size = this.getDimension(html);
			}
		}
	}
	this.drawer.clearBoard();
};
Graph.prototype.clearBoard = function(){
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
	this.drawer.clearBoard();
};
Graph.prototype.getDimension = function(html){
	if(this.parent){
		return this.parent.getDimension(html);
	}
	this.board.appendChild(html);
	var rect = html.getBoundingClientRect();
	var pos = new Pos(rect.width, rect.height);
	this.board.removeChild(html);
	return pos;
};
Graph.prototype.getButton = function(label){
	var button = document.createElement("button");
	button.innerHTML = label;
	button.className="ToolButton";
	button.graph = this;
	var that = this;
	button.addEventListener("click", function(e){that.optionButton(e);}, false);
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
Graph.prototype.insertNode = function(node, pos) {
	/* testing if node is already existing in the graph */
	if(!(node.id)){
		node.id = node.typ+"_"+pos;
	}
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
	for(var i in ownAssoc) {
		ownAssoc[i].calcOwnEdge();
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
	var minx=0, miny=0;
	for (var i in this.nodes) {
		var node = this.nodes[i];

		this.NodeMoveRaster(node);

		maxx=Math.max(maxx,node.x+node.width);
		maxy=Math.max(maxy,node.y+node.height);
		minx=Math.max(minx,node.x);
		miny=Math.max(miny,node.y);
	}
	var size = new Pos(Math.max(this.minSize.x, maxx+20), Math.max(this.minSize.y, maxy+50));
	this.drawer.setSize(this.board, size.x, size.y);
	if(this.drawer.showInfoBox()){
		this.infoBox.style.left = this.board.offsetWidth-200;
		this.infoBox.style.top = this.board.offsetHeight-42;
	}
	if(this.drawer.isShowRaster() && this.options.raster){
		this.drawRaster();
	}
	return size;
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
	if(this.loader.abort && this.loader.images.length>0){
		return;
	}

	if(this.drawer.showInfoBox()){
		this.infoBox = document.createElement("div");
	}
	this.resize();


	for(var i in this.nodes) {
		this.nodes[i].htmlNode = this.drawer.getHTMLNode(this.nodes[i], false);
		if(this.nodes[i].htmlNode){
			this.board.appendChild( this.nodes[i].htmlNode );
		}
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
		var that = this;
		this.infoBox.onmouseout = function (evt) {that.fadeout(evt);};
		this.infoBox.onmouseover= function (evt) {that.fadein(evt);};

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

Graph.prototype.layout = function(minwidth, minHeight){
	if(this.layouter){
		this.layouter.graph = this;
		this.layouter.layout(minwidth || 0, minHeight || 0);
	}
}
Graph.prototype.layouting = function(){
	if(this.layouter){
		this.initGraph();
		this.layouter.layout(this.minSize.x, this.minSize.y);
	}
}

//					######################################################### DRAG AND DROP #########################################################
Graph.prototype.initDragAndDrop = function(){
	this.objDrag = null;
	this.mouse = new Pos(0,0);
	this.offset= new Pos(0,0);
	var that = this;
	// its Root = this.board
	this.board.onmousemove = function(e){that.doDrag(e);};
	this.board.onmouseup = function(e){that.stopDrag(e);};
};
Graph.prototype.addNodeLister = function(htmlElement){
	var that = this;
	htmlElement.addEventListener("mousedown", function(e){that.startDrag(e);}, false);
	htmlElement.addEventListener("mouseover", function(e){that.showinfo(e);}, false);
	htmlElement.addEventListener("mouseout", function(e){that.fadeout(e);}, false);
};
Graph.prototype.showinfo = function(event){
	var objElem = event.currentTarget;
	var node=this.getGraphNode(objElem);
	if(node){
		var x = Math.round( objElem.style.left.substring(0,objElem.style.left.length-2) * 100)/100;
		var y = Math.round( objElem.style.top.substring(0,objElem.style.top.length-2) * 100)/100;
		node.parent.showInfoText("Box-Position: " + x + ":" + y);
	}
};
Graph.prototype.startDrag = function(event) {
	this.objDrag = event.currentTarget;
	this.offset.x = this.mouse.x - this.objDrag.offsetLeft;
	this.offset.y = this.mouse.y - this.objDrag.offsetTop;
};

Graph.prototype.doDrag = function(event) {
	this.mouse.x = (IE) ? window.event.clientX : event.pageX;
	this.mouse.y = (IE) ? window.event.clientY : event.pageY;

	if (this.objDrag != null) {
		this.objDrag.style.left = (this.mouse.x - this.offset.x) + "px";
		this.objDrag.style.top = (this.mouse.y - this.offset.y) + "px";

		window.status = "Box-Position: " + this.objDrag.style.left + ", " + this.objDrag.style.top;
		var model = this.savePosition();
		if(model){
			model.parent.drawLines();
		}
	}
}

Graph.prototype.getGraphNode = function(objElement){
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
};

Graph.prototype.savePosition = function(){
	var node=this.getGraphNode(this.objDrag);
	if(node){
		node.x = this.objDrag.offsetLeft;
		node.y = this.objDrag.offsetTop;
		return node;
	}
	return null;
};
Graph.prototype.fadein = function(evt){
	if(this.infoBox){this.infoBox.fader=false;}
}
Graph.prototype.fadeout = function(evt){
	if(this.infoBox&&!this.infoBox.fader){
		this.infoBox.fader=100;
		var that = this;
		setTimeout(function(){that.fadeOutTimer();}, 2000);
	}
};
Graph.prototype.stopDrag = function(evt) {
	var model = this.savePosition();
	this.objDrag = null;
	if(model){
		model.parent.drawLines();
		model.parent.resize();
	}
}

Graph.prototype.fadeOutTimer= function(){
	var item=this.infoBox;
	if(item && item.fader>0.00){
			item.style.opacity = (item.fader / 100);
			item.style.MozOpacity = (item.fader / 100);
			item.style.KhtmlOpacity = (item.fader / 100);
			item.fader-=5;
			var that = this;
			setTimeout(function(){that.fadeOutTimer()}, 20);
	}else{
		item.fader=0;
		item.style.display="none";
	}
}

Graph.prototype.optionButton = function(event){
	var btn = event.currentTarget;
	if(btn.innerHTML=="HTML"){
		btn.graph.drawer = new HTMLDrawer();
		btn.graph.options.display = "html";
		btn.graph.loader.init(true);
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
	}else if(btn.innerHTML=="SVG"){
		btn.graph.drawer = new SVGDrawer();
		btn.graph.options.display = "svg";
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
	}else if(btn.innerHTML=="SVG-Export"){
		btn.graph.drawer = new SVGDrawer();
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
		var size = btn.graph.resize();
		var img = document.createElement("img");
		img.src = "data:image/svg+xml;base64," + this.utf8_to_b64(this.serializeXmlNode(btn.graph.board));
		btn.graph.clearBoard();
		btn.graph.board = img;
		btn.graph.board.width = size.x;
		btn.graph.board.height = size.y;
		btn.graph.root.appendChild(img);
		//window.open("data:image/svg+xml," + escape(btn.graph.board.outerHTML));
	}else if(btn.innerHTML=="PNG-Export"){
		var oldDrawer = this.drawer;
		this.drawer = new CanvasDrawer();
		this.loader.init(false);
		this.loader.oldDrawer = oldDrawer;
		this.initGraph();
		this.drawGraph(0,0);
		this.loader.resetDrawer();
	}
}

Graph.prototype.serializeXmlNode = function(xmlNode) {
	if (typeof window.XMLSerializer != "undefined") {
		return (new window.XMLSerializer()).serializeToString(xmlNode);
	} else if (typeof xmlNode.xml != "undefined") {
		return xmlNode.xml;
	}
	return xmlNode.outerHTML;
}
Graph.prototype.utf8_to_b64 = function( str ) {
	return window.btoa(unescape(encodeURIComponent( str )));
}
//					######################################################### GraphLayout-Dagre #########################################################
DagreLayout = function() {};
DagreLayout.prototype.layout = function(width, height) {
	this.g = new dagre.Digraph();
	for (var i in this.graph.nodes) {
		var node = this.graph.nodes[i];
		this.g.addNode(node.id, {label: node.id, width:node.width+10, height:node.height+20, x:node.x, y:node.y});
	}
	for (var i in this.graph.edges) {
		var edges = this.graph.edges[i];
		edges.id = i;
		this.g.addEdge(i, edges.source.id, edges.target.id);
	}

	var layout = dagre.layout()
					.nodeSep(this.graph.options.nodeSep)
					.rankDir(this.graph.options.rank)
					.run(this.g);
	// Set the layouting back
	for (var i in this.graph.nodes) {
		var node = this.graph.nodes[i];
		var layoutNode = layout._nodes[node.id];
		node.x = layoutNode.value.x;
		node.y = layoutNode.value.y;
	}
	this.graph.drawGraph(width, height);
};

Loader = function(graph) {this.init(false);this.graph=graph;};
Loader.prototype.init = function(abort){
	this.images = [];
	this.abort=abort;
}
Loader.prototype.resetDrawer = function(){
	if(this.images.length==0){
		this.graph.drawer.onFinishImage();
	}else{
		var img = this.images.pop();
		this.graph.root.appendChild(img);
	}
};
Loader.prototype.remove = function(img){
	this.images.remove(img);
}
Loader.prototype.onLoad = function(img){
	var idx = this.images.indexOf(img);
	if (idx != -1) {
		this.images.splice(idx, 1);
	}
	if(this.images.length==0){
		this.graph.drawer.onFinishImage();
	}
};
Loader.prototype.appendImg = function(img){
	img.crossOrigin = 'anonymous';
	var that = this.graph.drawer;
	img.onload = function(e){that.onLoadImage(e);};
	this.images.push(img);
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
		this.addElement(board, this.htmlElement, drawer.createInfo(this.sourceinfo.pos.x, this.sourceinfo.pos.y, this.sourceproperty, false));
	}
	if(this.targetproperty){
		this.addElement(board, this.htmlElement, drawer.createInfo(this.targetinfo.pos.x, this.targetinfo.pos.y, this.targetproperty, false));
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
			/* OwnAssoc */
			return false;
		}
		// Must be UP_DOWN or DOWN_UP
		if(this.source.center.y<this.target.center.y){
			// UP_DOWN
			sourcePos = this.getCenterPosition(this.source, Edge.Position.DOWN);
			targetPos = this.getCenterPosition(this.target, Edge.Position.UP);
		}else{
			sourcePos = this.getCenterPosition(this.source, Edge.Position.UP);
			targetPos = this.getCenterPosition(this.source, Edge.Position.DOWN);
		}
	}else{
		this.m = (this.target.center.y - this.source.center.y) / divisor;
		this.n = this.source.center.y - (this.source.center.x * this.m);
		sourcePos = this.getPosition(this.m,this.n, this.source, this.target.center);
		targetPos = this.getPosition(this.m,this.n, this.target, sourcePos);
	}
	if(sourcePos&&targetPos){
		this.calcInfoPos( sourcePos, this.source, this.sourceinfo);
		this.calcInfoPos( targetPos, this.target, this.targetinfo);
		this.addEdgeToNode(this.source, sourcePos.id);
		this.addEdgeToNode(this.target, targetPos.id);
		this.path.push ( new Line(sourcePos, targetPos, this.lineStyle));
	}
	return true;
};
Edge.prototype.getCenterPosition = function(node, pos){
	if(pos==Edge.Position.DOWN){
		return new Pos(node.center.x, (node.y+node.height), Edge.Position.DOWN);
	}
	if(pos==Edge.Position.UP){
		return new Pos(node.center.x, node.y, Edge.Position.UP);
	}
	if(pos==Edge.Position.LEFT){
		return new Pos(node.x, node.center.y, Edge.Position.LEFT);
	}
	if(pos==Edge.Position.RIGHT){
		return new Pos(node.x+node.width, node.center.y, Edge.Position.RIGHT);
	}
}

Edge.prototype.calcOwnEdge = function(){
	//this.source
	var offset = 20;
	var start = this.getFree(this.source);
	var end="";
	if(start.length>0){
		end = this.getFree(this.source);
		if(end.length<1){
			if(start==Edge.Position.UP){
				node.RIGHT+=1;
				end = Edge.Position.RIGHT;
			}else if(start==Edge.Position.DOWN){
				node.LEFT+=1;
				end = Edge.Position.LEFT;
			}else if(start==Edge.Position.RIGHT){
				node.DOWN+=1;
				end = Edge.Position.DOWN;
			}else{
				node.UP+=1;
				end = Edge.Position.UP;
			}
		}
	}else{
		start = Edge.Position.RIGHT;
		end = Edge.Position.DOWN;
	}

	var sPos = this.getCenterPosition(this.source, start);
	var tPos;
	if(start==Edge.Position.UP){
		tPos = new Pos(sPos.x, sPos.y - offset);
	}else if(start==Edge.Position.DOWN){
		tPos = new Pos(sPos.x, sPos.y + offset);
	}else if(start==Edge.Position.RIGHT){
		tPos = new Pos(sPos.x + offset, sPos.y);
	}else if(start==Edge.Position.LEFT){
		tPos = new Pos(sPos.x - offset, sPos.y);
	}
	this.path.push (new Line(sPos, tPos, this.lineStyle));
	if(end==Edge.Position.LEFT || end==Edge.Position.RIGHT){
		if(start==Edge.Position.LEFT){
			sPos = tPos;
			tPos = new Pos(sPos.x, this.source.y - offset);
			this.path.push (new Line(sPos, tPos, this.lineStyle));
		}else if(start==Edge.Position.RIGHT){
			sPos = tPos;
			tPos = new Pos(sPos.x, this.source.y + offset);
			this.path.push (new Line(sPos, tPos, this.lineStyle));
		}
		sPos = tPos;
		if(end==Edge.Position.LEFT){
			tPos = new Pos(this.source.x - offset, sPos.y);
		}else{
			tPos = new Pos(this.source.x + this.source.width + offset, sPos.y);
		}
		this.path.push (new Line(sPos, tPos, this.lineStyle));
		sPos = tPos;
		tPos = new Pos(sPos.x, this.source.center.y);
		this.path.push (new Line(sPos, tPos, this.lineStyle));
	}else if(end==Edge.Position.UP || end==Edge.Position.DOWN){
		if(start==Edge.Position.UP){
			sPos = tPos;
			tPos = new Pos(this.source.x + this.source.width + offset, sPos.y);
			this.path.push (new Line(sPos, tPos, this.lineStyle));
		}else if(start==Edge.Position.DOWN){
			sPos = tPos;
			tPos = new Pos(this.source.x - offset, sPos.y);
			this.path.push (new Line(sPos, tPos, this.lineStyle));
		}
		sPos = tPos;
		if(end==Edge.Position.UP){
			tPos = new Pos(sPos.x, this.source.y - offset);
		}else{
			tPos = new Pos(sPos.x, this.source.y + this.source.height + offset);
		}
		this.path.push (new Line(sPos, tPos, this.lineStyle));
		sPos = tPos;
		tPos = new Pos(this.source.center.x, sPos.y);
		this.path.push (new Line(sPos, tPos, this.lineStyle));
	}
	sPos = tPos;
	this.path.push (new Line(sPos, this.getCenterPosition(this.source, end), this.lineStyle));
};
Edge.prototype.addEdgeToNode=function(node, pos){
	if(pos==Edge.Position.UP){
		node.UP+=1;
	}else if(pos==Edge.Position.DOWN){
		node.DOWN+=1;
	}else if(pos==Edge.Position.RIGHT){
		node.RIGHT+=1;
	}else if(pos==Edge.Position.LEFT){
		node.LEFT+=1;
	}
};
Edge.prototype.getFree = function(node){
	if(node.UP==0 ){
		node.UP +=1;
		return Edge.Position.UP;
	}else if(node.RIGHT==0 ){
		node.RIGHT +=1;
		return Edge.Position.RIGHT;
	}else if(node.DOWN==0 ){
		node.DOWN +=1;
		return Edge.Position.DOWN;
	}else if(node.LEFT==0 ){
		node.LEFT +=1;
		return Edge.Position.LEFT;
	}
	return "";
}

Edge.prototype.calcInfoPos = function(linePos, item, info){
	var newY = linePos.y;
	var newX = linePos.x;
	var yoffset =0;
	if(linePos.id==Edge.Position.UP){
		newY = newY - info.size.y -5;
		if(this.m>0){
			newX = (newY-this.n) / this.m + 5;
		}else{
			newX += 5;
		}
	}else if(linePos.id==Edge.Position.DOWN){
		newY = newY + 5;
		if(this.m>0){
			newX = (newY-this.n) / this.m + 5;
		}else{
			newX += 5;
		}
	}else if(linePos.id==Edge.Position.LEFT){
		newX -= info.size.x - 5;
		newY = (this.m * newX)+ this.n;
	}else if(linePos.id==Edge.Position.DOWN){
		newX += info.size.x + 5;
		newY = (this.m * newX)+ this.n;
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

	var startArrow	= this.endPos().source;
	var targetArrow	= this.endPos().target;
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

Implements = function() { this.init(); }
Implements.prototype = Object_create(Generalisation.prototype);
Implements.prototype.constructor = Implements;
Implements.prototype.initGeneralisation = Implements.prototype.init;
Implements.prototype.init =function(){
	this.initGeneralisation();
	this.lineStyle = Line.Format.DOTTED;
}

String.prototype.endsWith = function(suffix) {return this.indexOf(suffix, this.length - suffix.length) !== -1;};

function isIE () {
	var myNav = navigator.userAgent.toLowerCase();
	return (myNav.indexOf('msie') != -1 || myNav.endsWith('like gecko') );
}
