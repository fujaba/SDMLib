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

/* Info */
Info = function(info, parent, edge) { 
	this.property = info.property; 
	this.cardinality = info.cardinality; 
	this.id = info.id; 
	this.typ = "Info"; this.x = 0; this.y = 0; this.size = new Pos(0,0); this.center=new Pos(0,0); this.custom = false; this.parent = parent; this.edge = edge;}

Line = function(source, target, line, style) {this.source = source; this.target = target; this.line = line; this.style = style;}
Line.Format={SOLID:"SOLID", DOTTED:"DOTTED"};
/* Options */
Options = function(){
	this.canvasid = null;
	this.parent = null;
	this.subgraphs = [];
	
	// Options
	this.raster = false;
	this.display = "svg";
	this.font={};
	this.font["font-size"] = "10px";
	this.font["font-family"] = "Verdana";
	this.rank = "LR";			// Dagre TB, LR
	this.nodeSep = 20;
	this.infobox = false;
	this.CardinalityInfo = true;
	this.PropertyInfo = true;
	/*this.buttons = ["HTML", "SVG", "CANVAS", "SVG-Export", "PNG", "PDF"];*/
	this.buttons = ["HTML", "CANVAS", "SVG"];
}

/* Node */
GraphNode = function(id, content) {
	this.init();
	this.typ = "node";
	this.id = id;
	if(content){
		this.content = content;
	}
}
GraphNode.prototype.init = function() {
	this.x = 0;
	this.y = 0;
	this.width = 0;
	this.height = 0;
	this.edges = [];
	this.attributes = [];
	this.methods = [];
	this.RIGHT = this.LEFT = this.UP = this.DOWN=0;
};

GraphNode.prototype.removeFromBoard = function(board){
	if(this.htmlNode){
		board.removeChild(this.htmlNode);
		this.htmlNode = null;
	}
};
GraphNode.prototype.getX = function(){
	if(this.parent){
		return this.x + this.parent.getX();
	}
	if(this.x!=null){
		return this.x;
	}
	return 0;
};
GraphNode.prototype.getY = function(){
	if(this.parent){
		return this.y + this.parent.getY();
	}
	if(this.y!=null){
		return this.y;
	}
	return 0;
};
GraphNode.prototype.getRoot = function() {
	if(this.parent){
		return this.parent.getRoot();
	}
	return this;
}

/* Graph */
Graph = function(json, options) {
	this.init();
	this.nodeCount=0;
	this.nodes = {};
	this.edges = [];
	this.typ = json.typ;
	if(json.info){
		this.info = json.info;
	}
	if(json.style){
		this.style = json.style;
	}
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

	if(json.nodes) {
		for (var i = 0; i < json.nodes.length; i++) {
			this.insertNode(json.nodes[i], i);
		}
	}
	if(json.edges) {
		for (var i = 0; i < json.edges.length; i++){
			var e = json.edges[i];
			var edge;
			if(e.typ.toLowerCase()=="generalisation"){
				edge = new Generalisation();
			}else if(e.typ.toLowerCase()=="implements"){
				edge = new Implements();
			}else{
				edge = new Edge();
			}
			edge.source = this.getNode(e.source.id);
			edge.info = e.info;
			edge.style = e.style;
			edge.sourceInfo = new Info(e.source, this, edge);
			edge.targetInfo = new Info(e.target, this, edge);
			edge.source.edges.push(edge);

			edge.target = this.getNode(e.target.id);
			edge.target.edges.push(edge);
			this.edges.push(edge);
		}
	}
	if(!this.options.parent){
		if(this.options.canvasid){
			this.root = document.getElementById(this.options.canvasid);
		}
		if(!this.root){
			this.root = document.createElement("div");
			if(this.options.canvasid){
				this.root.id = this.options.canvasid;
			}
			document.body.appendChild(this.root);document.body.appendChild(this.root);
		}
		if(this.options.buttons.length>0){
			this.optionbar = document.createElement("div");
			this.optionbar.className = "Options";
			this.root.appendChild(this.optionbar);
			for(var i=0; i< this.options.buttons.length; i++){
				this.optionbar.appendChild(this.getButton(this.options.buttons[i]));
			}
			this.optionbar.appendChild(document.createElement("br"));
		}
		this.initGraph();
	}
};
Graph.prototype.getX = function(){
	if(this.parent){
		return this.x + this.parent.getX();
	}
	if(this.x!=null){
		return this.x;
	}
	return 0;
};
Graph.prototype.getY = function(){
	if(this.parent){
		return this.y + this.parent.getY();
	}
	if(this.y!=null){
		return this.y;
	}
	return 0;
};
Graph.prototype.getRoot = function() {
	if(this.parent){
		return this.parent.getRoot();
	}
	return this;
}
Graph.prototype = Object_create(GraphNode.prototype);
Graph.prototype.copy = function(source, target){
	for (var key in source) {
		target[key] = source[key];
	}
	if(source.width){
		target.startWidth = source.width;
	}
	if(source.height){
		node.startHeight = node.height;
	}
	return target;
};

Graph.prototype.removeFromBoard = function(board){
	if(this.htmlNode){
		board.removeChild(this.htmlNode);
		this.htmlNode = null;
	}
	this.board = null;
};

Graph.prototype.merge = function(ref, sourceA, sourceB) {
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
	if(this.root){
		this.clearBoard();
		this.board = this.drawer.createContainer(this);
		this.initDragAndDrop();
		this.root.appendChild(this.board);
	}

	for (var i in this.nodes) {
		var node = this.nodes[i];
		if(node.typ=="objectdiagram" || node.typ=="classdiagram"){
			node.root = this.board;
			node.initDrawer(this.options.display);
			node.drawer.graph = node;
			node.initGraph();
		}
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

	for (var i=0; i<this.edges.length;i++) {
		var edge = this.edges[i];
		this.initInfo(edge, edge.sourceInfo);
		this.initInfo(edge, edge.targetInfo);
	}
	this.drawer.clearBoard();
};

Graph.prototype.initInfo = function(edge, info){
	if(!this.options.CardinalityInfo && !this.options.PropertyInfo){
		return null;
	}

	var infoTxt = edge.getInfo(info, this.options.CardinalityInfo, this.options.PropertyInfo);
	if(infoTxt.length > 0) {
		var html = this.drawer.createInfo(info, true, infoTxt);
		if(html){
			info.size = this.getDimension(html);
		}
	}
	return infoTxt;
};

Graph.prototype.clearBoard = function(){
	if(this.board){
		this.clearLines();
		for(var i in this.nodes) {
			this.nodes[i].removeFromBoard(this.board);
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
	if(!this.board){
		return new Pos();
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
	bindEvent(button, "click", function(e){that.optionButton(e);});
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
Graph.prototype.getNode = function(id, isSub) {
	if(this.nodes[id]) {
		return this.nodes[id];
	}
	for(var i = 0;i < this.options.subgraphs.length;i++){
		var r = this.options.subgraphs[i].getNode(id, true);
		if(r) {
			return r;
		}
	}
	if(!isSub){
		this.nodes[id] = new GraphNode(id);
		this.nodes[id].parent = this;
		this.nodeCount++;
		return this.nodes[id];
	}
	return null;
}

Graph.prototype.addSubGraph = function(subgraph) {
	this.options.subgraphs.push(subgraph);
	if(this.parent) {
		this.parent.addSubGraph(subgraph);
	}
};

Graph.prototype.insertNode = function(node, pos) {
	/* testing if node is already existing in the graph */
	node.typ = node.typ.toLowerCase();
	if(node.typ=="objectdiagram" || node.typ=="classdiagram") {
		if(!this.options) {
			return;
		}
		var options = new Options();
		options.parent = this;
		options = this.merge(options, node.options);
		node = new Graph(node, options);
		options.rootElement = node;
		this.addSubGraph(node);
	}else {
		node = this.copy(node, new GraphNode());
	}
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
	var ownAssoc = [];
	for(var i = 0; i < this.edges.length; i++) {
		if(!this.edges[i].calculate(this.board, this.drawer)){
			ownAssoc.push(this.edges[i]);
		}
	}
	for(var i = 0; i < ownAssoc.length; i++) {
		ownAssoc[i].calcOwnEdge();
		var sourcePos = ownAssoc[i].getCenterPosition(ownAssoc[i].source, ownAssoc[i].start);
		ownAssoc[i].calcInfoPos( sourcePos, ownAssoc[i].source, ownAssoc[i].sourceInfo);
		
		sourcePos = ownAssoc[i].getCenterPosition(ownAssoc[i].target, ownAssoc[i].end);
		ownAssoc[i].calcInfoPos( sourcePos, ownAssoc[i].target, ownAssoc[i].targetInfo);
	}

	for(var i = 0; i < this.edges.length; i++) {
		this.edges[i].draw(this.board, this.drawer);
	}

};
Graph.prototype.clearLines = function(){
	for(var i=0; i<this.edges.length;i++) {
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

		this.moveToRaster(node);

		maxx=Math.max(maxx,node.x+node.width);
		maxy=Math.max(maxy,node.y+node.height);
		minx=Math.max(minx,node.x);
		miny=Math.max(miny,node.y);
	}
	var size = new Pos(Math.max(this.minSize.x, maxx+20), Math.max(this.minSize.y, maxy+50));
	this.drawer.setSize(this.board, size.x, size.y);
	if(this.drawer.showInfoBox() && this.options.infobox){
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

	if(this.drawer.showInfoBox() && this.options.infobox){
		this.infoBox = document.createElement("div");
	}
	if(!this.board) {
		this.initGraph();
	}
	this.resize();

	this.drawLines();
	
	for(var i in this.nodes) {
		var node = this.nodes[i];
		node.htmlNode = this.drawer.getHTMLNode(node, false);
		if(node.htmlNode){
			this.board.appendChild( node.htmlNode );
		}
	}
	
	if(this.drawer.showInfoBox() && this.options.infobox){
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
	if(!this.infoBox){
		return;
	}
	this.infoBox.fader=false;
	this.infoBox.innerHTML = text;
	this.infoBox.style.opacity = 100;
	this.infoBox.style.MozOpacity = 100;
	this.infoBox.style.KhtmlOpacity = 100;
	this.infoBox.style.display="";
};
Graph.prototype.moveToRaster = function(node){
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
		this.layouter.graph = this;
		this.layouter.layout(this.minSize.x, this.minSize.y);
	}
}
Graph.prototype.layoutCalculate = function(){
	if(this.layouter){
		this.layouter.graph = this;
		this.layouter.layout(this.minSize.x || 0, this.minSize.y || 0);
	}
}
//					######################################################### DRAG AND DROP #########################################################
Graph.prototype.initDragAndDrop = function(){
	this.objDrag = null;
	this.mouse = new Pos(0,0);
	this.offset= new Pos(0,0);
	this.startObj= new Pos(0,0);
	var that = this;
	// its Root = this.board
	this.board.onmousemove = function(e){that.doDrag(e);};
	this.board.onmouseup = function(e){that.stopDrag(e);};
	this.board.onmouseout = function(e){that.stopDrag(e);};
};
Graph.prototype.addNodeLister = function(element, node){
	var that = this;
	element.node = node;
	bindEvent(element, "mousedown", function(e){that.startDrag(e);});
	bindEvent(element, "mouseover", function(e){that.showinfo(e);});
	bindEvent(element, "mouseout", function(e){that.fadeout(e);});
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
Graph.prototype.setSelectable = function(node, value) {
    if (node.nodeType == 1) {
        node.setAttribute("unselectable", value);
    }
    var child = node.firstChild;
    while (child) {
        this.setSelectable(child, value);
        child = child.nextSibling;
    }
};

Graph.prototype.startDrag = function(event) {
	if(!event.currentTarget.isdraggable){
		return;
	}
	if(this.objDrag){
		return;
	}
	this.objDrag = event.currentTarget;
	var graph = this.objDrag.parentElement;
	if(graph) {
		for(var i=0;i<graph.children.length;i++) {
			this.setSelectable(graph.children[i], "on");
		}
	}
	this.offset.x = (IE) ? window.event.clientX : event.pageX;
	this.offset.y = (IE) ? window.event.clientY : event.pageY;
	this.startObj.x = this.objDrag.node.x;
	this.startObj.y = this.objDrag.node.y;
};

Graph.prototype.doDrag = function(event) {
	this.mouse.x = (IE) ? window.event.clientX : event.pageX;
	this.mouse.y = (IE) ? window.event.clientY : event.pageY;

	if (this.objDrag != null) {
		var x =(this.mouse.x - this.offset.x) + this.startObj.x;
		var y =(this.mouse.y - this.offset.y) + this.startObj.y;

		if(this.options.display=="svg"){
			x = x - this.startObj.x;
			y = y - this.startObj.y;
			this.objDrag.setAttribute('transform', "translate("+x+" "+y+")");
		} else {
			this.drawer.setPos(this.objDrag, x, y);
			if(this.objDrag.node){
				this.objDrag.node.x = x;
				this.objDrag.node.y = y;
				this.objDrag.node.parent.drawLines();
			}
		}
	}
}
Graph.prototype.stopDrag = function(event) {
	if(!this.objDrag){
		return;
	}
	if(!(event.type=="mouseup"||event.type=="mouseout")&&!event.currentTarget.isdraggable){
		return;
	}
	var item = this.objDrag;
	this.objDrag = null;
	var graph = item.parentElement;
	if(graph) {
		for(var i=0;i<graph.children.length;i++) {
			this.setSelectable(graph.children[i], "");
		}
	}
	if(item.node){
		if(this.options.display=="svg"){
			if(item.getAttributeNS(null, "transform")){
				var pos = item.getAttributeNS(null, "transform").slice(10,-1).split(' ');
				item.node.x = item.node.x + Number(pos[0]);
				item.node.y = item.node.y + Number(pos[1]);
			}
			
			this.board.removeChild(item);

			if(item.node.typ=="Info") {
				item.node.custom = true;
				item.node.edge.removeElement(item);
				var options = this.drawer.graph.options;
				var infoTxt = item.node.edge.getInfo(item.node, options.CardinalityInfo, options.PropertyInfo);
				item.node.edge.addElement(this.board, this.drawer.createInfo(item.node, false, infoTxt));
			}else{
				item.node.htmlNode = this.drawer.getHTMLNode(item.node, false);
				if(item.node.htmlNode){
					this.board.appendChild( item.node.htmlNode );
				}
				for(var i=0;i<item.node.edges.length;i++){
					var edge = item.node.edges[i];
					edge.sourceInfo.custom = false;
					edge.targetInfo.custom = false;
				}
			}
		}
		item.node.parent.drawLines();
		item.node.parent.resize();
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
Graph.prototype.initDrawer = function(typ){
	typ = typ.toLowerCase();
	this.options.display = typ;
	if(typ=="html"){
		this.drawer = new HTMLDrawer();
	}else if(typ=="svg"){
		this.drawer = new SVGDrawer();
	}else if(typ=="canvas"){
		this.drawer = new CanvasDrawer();
	}
};


Graph.prototype.optionButton = function(event){
	var btn = event.currentTarget;
	if(btn.innerHTML=="HTML"){
		btn.graph.initDrawer(btn.innerHTML);
		btn.graph.loader.init(true);
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
	}else if(btn.innerHTML=="SVG"){
		btn.graph.initDrawer(btn.innerHTML);
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
	}else if(btn.innerHTML=="CANVAS"){
		btn.graph.initDrawer(btn.innerHTML);
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0)
	}else if(btn.innerHTML=="PNG"){
		var oldDrawer = this.drawer;
		this.drawer = new CanvasDrawer();
		this.loader.init(false);
		this.loader.oldDrawer = oldDrawer;
		this.initGraph();
		this.drawGraph(0,0);
		this.loader.resetDrawer();
	}else if(btn.innerHTML=="PDF"){
		var oldDrawer = btn.graph.drawer;
		btn.graph.drawer = new SVGDrawer();
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
		var svg = this.serializeXmlNode(btn.graph.board);

		var pdf = new jsPDF('l', 'pt', 'a4');
		svgElementToPdf(svg, pdf, {removeInvalid: false});
		pdf.save('Download.pdf');

		btn.graph.drawer = oldDrawer;
		btn.graph.initGraph();
		btn.graph.drawGraph(0,0);
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

Graph.prototype.SaveAs = function () {
	var image = new Image();
	image.src = 'data:image/svg+xml;base64,' + this.utf8_to_b64(this.serializeXmlNode(this.board));
	image.onload = function(e) {
		var canvas = document.createElement('canvas');
		canvas.width = image.width;
		canvas.height = image.height;
		var context = canvas.getContext('2d');
		context.drawImage(image, 0, 0);
	    var a = document.createElement('a');
		a.download = "download.png";
		a.href = canvas.toDataURL('image/png');
		a.click();
	};
	this.Save("image/svg+xml", this.serializeXmlNode(this.board), "download.svg");
};
Graph.prototype.Save = function (typ, data, name) {
	var a = document.createElement("a");
	var url = window.URL.createObjectURL(new Blob([data], {type: typ}));
	a.href = url;
	a.download = name;
	a.click();
}

Graph.prototype.Export = function () {
	var result = {};
	result.typ = this.typ;
	result.options = {};
	

	for (var key in this.options) {
		if(this.options[key] != null) {
			result.options[key] = this.options[key];
		}
	}
	var items = [];
	var add = false;
	for (var i in this.nodes) {
		var n = this.nodes[i];
		var newNode = {typ:n.typ, id:n.id, x: n.x, y:n.y, width:n.width, height:n.height };
		if(n.attributes && n.attributes.length > 0){
			newNode.attributes=[];
			for(var a=0;a<n.attributes.length;a++){
				newNode.attributes.push(n.attributes[a]);
			}
		}
		if(n.methods && n.methods.length > 0){
			newNode.methods=[];
			for(var m=0;m<n.methods.length;m++){
				newNode.methods.push(n.methods[m]);
			}
		}
		items.push(newNode);
		add = true;
	}
	if(add){result.nodes=items;}
	items =[];add=false;
	for (var i=0;i< this.edges.length;i++) {
		var e = this.edges[i];
		var newEdge = {typ:e.typ, source: {id: e.source.id}, target: {id: e.target.id}};
		
		if(e.sourceInfo.cardinality){
			newEdge.source["cardinality"] = e.sourceInfo.cardinality;
		}
		if(e.sourceInfo.property){
			newEdge.source["property"] = e.sourceInfo.property;
		}
		if(e.targetInfo.cardinality){
			newEdge.target["cardinality"] = e.targetInfo.cardinality;
		}
		if(e.targetInfo.property){
			newEdge.target["property"] = e.targetInfo.property;
		}
		items.push(newEdge);
		add = true;
	}
	if(add){result.edges=items;}

	var data="<html><head>"+document.head.innerHTML.trim()+"</head><body><script>"
		+"new Graph("+JSON.stringify(result, null, "\t") +").layout();</script></body></html>";
	this.Save("text/json", data, "download.html");
}		

//					######################################################### GraphLayout-Dagre #########################################################
DagreLayout = function() {};
DagreLayout.prototype.layout = function(width, height) {
	this.g = new dagre.Digraph();
	for (var i in this.graph.nodes) {
		var node = this.graph.nodes[i];
		this.g.addNode(node.id, {label: node.id, width:node.width+10, height:node.height+20, x:node.x, y:node.y});
	}
	for (var i = 0; i < this.graph.edges.length; i++) {
		var edges = this.graph.edges[i];
		edges.id = i;
		this.g.addEdge(i, this.getRootNode(edges.source).id, this.getRootNode(edges.target).id);
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
}
DagreLayout.prototype.getRootNode = function(node, child) {
	if(node.parent){
		return this.getRootNode(node.parent, node);
	}
	if(!child){
		return node;
	}
	return child;
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
Edge = function() {this.init();this.typ="EDGE";}
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

Edge.prototype.addElement = function(board, element){
	if(element){
		this.htmlElement.push(element);board.appendChild(element);
	}
};
Edge.prototype.removeElement = function(element){
	if(element){
		for(var i=0;i<this.htmlElement.length;i++){
			if(this.htmlElement[i]==element){
				this.htmlElement.splice(i, 1);
				i--;
			}
		}
	}
};

Edge.prototype.calculate = function(board, drawer){
	this.source.center = new Pos(this.source.getX() + (this.source.width / 2), this.source.getY() + (this.source.height / 2));
	this.target.center = new Pos(this.target.getX() + (this.target.width / 2), this.target.getY() + (this.target.height / 2));
	return this.calcCenterLine();
}
Edge.prototype.draw = function(board, drawer){
	for(var i=0;i<this.path.length;i++){
		var p = this.path[i];
		this.addElement(board, drawer.createLine(p.source.x, p.source.y, p.target.x, p.target.y, p.line, p.style));
	}
	var options = drawer.graph.options;
	this.drawSourceText(board, drawer, options);
	this.drawTargetText(board, drawer, options);
};
Edge.prototype.drawSourceText = function(board, drawer, options){
	var infoTxt = this.getInfo(this.sourceInfo, options.CardinalityInfo, options.PropertyInfo);
	if(infoTxt.length > 0 ){
		this.addElement(board, drawer.createInfo(this.sourceInfo, false, infoTxt));
	}
}
Edge.prototype.drawTargetText = function(board, drawer, options){
	var infoTxt = this.getInfo(this.targetInfo, options.CardinalityInfo, options.PropertyInfo);
	if(infoTxt.length > 0 ){
		this.addElement(board, drawer.createInfo(this.targetInfo, false, infoTxt));
	}
}


Edge.prototype.endPos = function(){
	return this.path[this.path.length-1];
}

Edge.prototype.edgePosition = function() {
	var pos=0;
	for(var i=0;i < this.source.edges.length; i++) {
		if(this.source.edges[i] == this){
			return pos;
		}
		if(this.source.edges[i].target == this.target){
			pos ++;
		}
	}
	return pos;
};
Edge.prototype.calcCenterLine = function(){
	var divisor = (this.target.center.x - this.source.center.x);
	var sourcePos,targetPos;
	var edgePos = this.edgePosition() * 10;

	this.path = new Array();
	if(divisor==0){
		if(this.source==this.target){
			/* OwnAssoc */
			return false;
		}
		// Must be UP_DOWN or DOWN_UP
		if(this.source.center.y<this.target.center.y){
			// UP_DOWN
			sourcePos = this.getCenterPosition(this.source, Edge.Position.DOWN, edgePos);
			targetPos = this.getCenterPosition(this.target, Edge.Position.UP, edgePos);
		}else{
			sourcePos = this.getCenterPosition(this.source, Edge.Position.UP, edgePos);
			targetPos = this.getCenterPosition(this.source, Edge.Position.DOWN, edgePos);
		}
	}else{
		this.m = (this.target.center.y - this.source.center.y) / divisor;
		this.n = this.source.center.y - (this.source.center.x * this.m);
		sourcePos = this.getPosition(this.m,this.n, this.source, this.target.center, edgePos);
		targetPos = this.getPosition(this.m,this.n, this.target, sourcePos, edgePos);
	}
	if(sourcePos&&targetPos){
		this.calcInfoPos( sourcePos, this.source, this.sourceInfo, edgePos);
		this.calcInfoPos( targetPos, this.target, this.targetInfo, edgePos);
		this.addEdgeToNode(this.source, sourcePos.id);
		this.addEdgeToNode(this.target, targetPos.id);
		this.path.push ( new Line(sourcePos, targetPos, this.lineStyle, this.style));
	}
	return true;
};
Edge.prototype.getCenterPosition = function(node, pos, offset){
	if (!offset) {
		offset = 0;
	}
	if(pos==Edge.Position.DOWN){
		return new Pos(node.center.x + offset, (node.y+node.height), Edge.Position.DOWN);
	}
	if(pos==Edge.Position.UP){
		return new Pos(node.center.x + offset, node.y, Edge.Position.UP);
	}
	if(pos==Edge.Position.LEFT){
		return new Pos(node.x, node.center.y + offset, Edge.Position.LEFT);
	}
	if(pos==Edge.Position.RIGHT){
		return new Pos(node.x+node.width, node.center.y + offset, Edge.Position.RIGHT);
	}
}
Edge.prototype.getInfo = function(info, showCardinality, showProperty){
	var infoTxt = "";
	if(showCardinality && info.property){
		infoTxt = info.property;
	}
	if(showProperty && info.cardinality){
		if(infoTxt.length > 0 ){
			infoTxt += "\n";
		}
		if(info.cardinality.toLowerCase() == "one"){
			infoTxt += "0..1";
		}else if(info.cardinality.toLowerCase() == "many"){
			infoTxt += "0..*";
		}
	}
	return infoTxt;
}
Edge.prototype.calcOwnEdge = function(){
	//this.source
	var offset = 20;
	this.start = this.getFree(this.source);
	if(this.start.length>0){
		this.end = this.getFreeOwn(this.source, this.start);
	}else{
		this.start = Edge.Position.RIGHT;
		this.end = Edge.Position.DOWN;
	}

	var sPos = this.getCenterPosition(this.source, this.start);
	var tPos;
	if(this.start==Edge.Position.UP){
		tPos = new Pos(sPos.x, sPos.y - offset);
	}else if(this.start==Edge.Position.DOWN){
		tPos = new Pos(sPos.x, sPos.y + offset);
	}else if(this.start==Edge.Position.RIGHT){
		tPos = new Pos(sPos.x + offset, sPos.y);
	}else if(this.start==Edge.Position.LEFT){
		tPos = new Pos(sPos.x - offset, sPos.y);
	}
	this.path.push (new Line(sPos, tPos, this.lineStyle));
	if(this.end==Edge.Position.LEFT || this.end==Edge.Position.RIGHT){
		if(this.start==Edge.Position.LEFT){
			sPos = tPos;
			tPos = new Pos(sPos.x, this.source.y - offset);
			this.path.push (new Line(sPos, tPos, this.lineStyle));
		}else if(this.start==Edge.Position.RIGHT){
			sPos = tPos;
			tPos = new Pos(sPos.x, this.source.y + offset);
			this.path.push (new Line(sPos, tPos, this.lineStyle));
		}
		sPos = tPos;
		if(this.end==Edge.Position.LEFT){
			tPos = new Pos(this.source.x - offset, sPos.y);
		}else{
			tPos = new Pos(this.source.x + this.source.width + offset, sPos.y);
		}
		this.path.push (new Line(sPos, tPos, this.lineStyle));
		sPos = tPos;
		tPos = new Pos(sPos.x, this.source.center.y);
		this.path.push (new Line(sPos, tPos, this.lineStyle));
	}else if(this.end==Edge.Position.UP || this.end==Edge.Position.DOWN){
		if(this.start==Edge.Position.UP){
			sPos = tPos;
			tPos = new Pos(this.source.x + this.source.width + offset, sPos.y);
			this.path.push (new Line(sPos, tPos, this.lineStyle));
		}else if(this.start==Edge.Position.DOWN){
			sPos = tPos;
			tPos = new Pos(this.source.x - offset, sPos.y);
			this.path.push (new Line(sPos, tPos, this.lineStyle));
		}
		sPos = tPos;
		if(this.end==Edge.Position.UP){
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
	this.path.push (new Line(sPos, this.getCenterPosition(this.source, this.end), this.lineStyle));
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

Edge.prototype.getFreeOwn = function(node, start){
	var list = new Array(Edge.Position.UP, Edge.Position.RIGHT, Edge.Position.DOWN, 
		Edge.Position.LEFT, Edge.Position.UP, Edge.Position.RIGHT, Edge.Position.DOWN);
	var result = new Array();
	var id=0;
	for(var i=0;i<list.length;i++) {
		if(list[i]==start) {
			id =i;
			break;
		}
	}
	if(node[list[id + 1]] == 0  || node[list[id + 1]] < node[list[id + 3]]) {
		node[list[id + 1]] ++;
		return list[id + 1];
	}
	//if(node[list[id + 3]] == 0 ) {
	node[list[id + 3]]++;
	return list[id + 3];
	//}
}

Edge.prototype.calcInfoPos = function(linePos, item, info, offset){
	// Manuell move the InfoTag
	if(info.custom){
		return;
	}
	if(!offset){
		offset = 0;
	}
	var newY = linePos.y;
	var newX = linePos.x;
	var yoffset =0;
	if(linePos.id==Edge.Position.UP){
		newY = newY - info.size.y -5 + (offset);
		if(this.m>0){
			newX = (newY-this.n) / this.m + 5 + offset;
		}else{
			newX += 5 + offset;
		}
	}else if(linePos.id==Edge.Position.DOWN){
		newY = newY + 5 + (offset);
		if(this.m>0){
			newX = (newY-this.n) / this.m + 5 + offset;
		}else{
			newX += 5 + offset;
		}
	}else if(linePos.id==Edge.Position.LEFT){
		newX -= info.size.x - 5;
		if(this.m>0){
			newY = (this.m * newX)+ this.n + offset;
		}else{
			newY += 5 + offset;
		}
	}else if(linePos.id==Edge.Position.RIGHT){
		newX += info.size.x + 5;
		if(this.m>0){
			newY = (this.m * newX)+ this.n + offset;
		}else{
			newY += 5 + offset;
		}
	}
	info.x = newX;
	info.y = newY;
};

Edge.prototype.getPosition= function(m , n, entity, refCenter, offset){
	if (!offset) {
		offset = 0;
	}
	var x,y;
	var pos=new Array();
	var distance=new Array();
	x = entity.getX()+entity.width;
	y = m*x+n;
	if(y>=entity.getY() && y<=(entity.getY()+entity.height)){
		pos.push(new Pos(x , y + offset, Edge.Position.RIGHT));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	y = entity.getY();
	x = (y-n)/m;
	if(x>=entity.getX() && x<=(entity.getX()+entity.width)){
		pos.push(new Pos(x  + offset, y, Edge.Position.UP));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	x = entity.getX();
	y = m*x+n;
	if(y>=entity.getY() && y<=(entity.getY()+entity.height)){
		pos.push(new Pos(x , y + offset, Edge.Position.LEFT));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	y = entity.getY()+entity.height;
	x = (y-n)/m;
	if(x>=entity.getX() && x<=(entity.getX()+entity.width)){
		pos.push(new Pos(x + offset, y, Edge.Position.DOWN));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	var min=999999999;
	var position;
	for(var i=0;i<pos.length;i++){
		if(distance[i]<min){
			 min = distance[i];
			 position = pos[i];
		}
	}
	return position;
};
Generalisation = function() { this.init();this.typ="Generalisation";};
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
	this.addElement(board, drawer.createLine(this.top.x, this.top.y, this.end.x, this.end.y, this.lineStyle));
	this.addElement(board, drawer.createLine(this.bot.x, this.bot.y, this.end.x, this.end.y, this.lineStyle));
	this.addElement(board, drawer.createLine(this.top.x, this.top.y, this.bot.x, this.bot.y, this.lineStyle));
};
Generalisation.prototype.drawSourceText = function(board, drawer, options){};
Generalisation.prototype.drawTargetText = function(board, drawer, options){};

Implements = function() { this.init();this.typ="Implements";}
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
function bindEvent(el, eventName, eventHandler) {
  if (el.addEventListener){
    el.addEventListener(eventName, eventHandler, false); 
  } else if (el.attachEvent){
    el.attachEvent('on'+eventName, eventHandler);
  }
}



/**
 * A class to parse color values
 * @author Stoyan Stefanov <sstoo@gmail.com>
 * @link   http://www.phpied.com/rgb-color-parser-in-javascript/
 * @license Use it if you like it
 */
function RGBColor(value)
{
	this.ok = false;
	// strip any leading #
	if (value.charAt(0) == '#') { // remove # if any
		value = value.substr(1,6);
	}
	value = value.replace(/ /g,'').toLowerCase();

	// before getting into regexps, try simple matches
	// and overwrite the input
	var simple_colors = {
		aliceblue: 'f0f8ff', antiquewhite: 'faebd7',
		aqua: '00ffff', aquamarine: '7fffd4',
		azure: 'f0ffff', beige: 'f5f5dc',
		bisque: 'ffe4c4', black: '000000',
		blanchedalmond: 'ffebcd', blue: '0000ff',
		blueviolet: '8a2be2', brown: 'a52a2a',
		burlywood: 'deb887', cadetblue: '5f9ea0',
		chartreuse: '7fff00', chocolate: 'd2691e',
		coral: 'ff7f50', cornflowerblue: '6495ed',
		cornsilk: 'fff8dc', crimson: 'dc143c',
		cyan: '00ffff', darkblue: '00008b',
		darkcyan: '008b8b', darkgoldenrod: 'b8860b',
		darkgray: 'a9a9a9', darkgreen: '006400',
		darkkhaki: 'bdb76b', darkmagenta: '8b008b',
		darkolivegreen: '556b2f', darkorange: 'ff8c00',
		darkorchid: '9932cc', darkred: '8b0000',
		darksalmon: 'e9967a', darkseagreen: '8fbc8f',
		darkslateblue: '483d8b', darkslategray: '2f4f4f',
		darkturquoise: '00ced1', darkviolet: '9400d3',
		deeppink: 'ff1493', deepskyblue: '00bfff',
		dimgray: '696969', dodgerblue: '1e90ff',
		feldspar: 'd19275', firebrick: 'b22222',
		floralwhite: 'fffaf0', forestgreen: '228b22',
		fuchsia: 'ff00ff', gainsboro: 'dcdcdc',
		ghostwhite: 'f8f8ff', gold: 'ffd700',
		goldenrod: 'daa520', gray: '808080', 
		green: '008000', greenyellow: 'adff2f',
		honeydew: 'f0fff0', hotpink: 'ff69b4',
		indianred : 'cd5c5c', indigo : '4b0082',
		ivory: 'fffff0', khaki: 'f0e68c',
		lavender: 'e6e6fa', lavenderblush: 'fff0f5',
		lawngreen: '7cfc00', lemonchiffon: 'fffacd',
		lightblue: 'add8e6', lightcoral: 'f08080',
		lightcyan: 'e0ffff', lightgoldenrodyellow: 'fafad2',
		lightgrey: 'd3d3d3', lightgreen: '90ee90',
		lightpink: 'ffb6c1', lightsalmon: 'ffa07a',
		lightseagreen: '20b2aa', lightskyblue: '87cefa',
		lightslateblue: '8470ff', lightslategray: '778899',
		lightsteelblue: 'b0c4de', lightyellow: 'ffffe0',
		lime: '00ff00', limegreen: '32cd32',
		linen: 'faf0e6', magenta: 'ff00ff',
		maroon: '800000', mediumaquamarine: '66cdaa',
		mediumblue: '0000cd', mediumorchid: 'ba55d3',
		mediumpurple: '9370d8', mediumseagreen: '3cb371',
		mediumslateblue: '7b68ee', mediumspringgreen: '00fa9a',
		mediumturquoise: '48d1cc', mediumvioletred: 'c71585',
		midnightblue: '191970', mintcream: 'f5fffa',
		mistyrose: 'ffe4e1', moccasin: 'ffe4b5',
		navajowhite: 'ffdead', navy: '000080',
		oldlace: 'fdf5e6', olive: '808000',
		olivedrab: '6b8e23', orange: 'ffa500',
		orangered: 'ff4500', orchid: 'da70d6',
		palegoldenrod: 'eee8aa', palegreen: '98fb98',
		paleturquoise: 'afeeee', palevioletred: 'd87093',
		papayawhip: 'ffefd5', peachpuff: 'ffdab9',
		peru: 'cd853f', pink: 'ffc0cb',
		plum: 'dda0dd', powderblue: 'b0e0e6',
		purple: '800080', red: 'ff0000',
		rosybrown: 'bc8f8f', royalblue: '4169e1',
		saddlebrown: '8b4513', salmon: 'fa8072',
		sandybrown: 'f4a460', seagreen: '2e8b57',
		seashell: 'fff5ee', sienna: 'a0522d',
		silver: 'c0c0c0', skyblue: '87ceeb',
		slateblue: '6a5acd', slategray: '708090',
		snow: 'fffafa', springgreen: '00ff7f',
		steelblue: '4682b4', tan: 'd2b48c',
		teal: '008080', thistle: 'd8bfd8',
		tomato: 'ff6347', turquoise: '40e0d0',
		violet: 'ee82ee', violetred: 'd02090',
		wheat: 'f5deb3', white: 'ffffff',
		whitesmoke: 'f5f5f5', yellow: 'ffff00',
		yellowgreen: '9acd32'
    };
	if(simple_colors[value]){
		value = simple_colors[value];
	}
	// array of color definition objects
    var color_defs = [
		{
			re: /^rgb\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3})\)$/,
			example: ['rgb(123, 234, 45)', 'rgb(255,234,245)'],
			process: function (bits){
				return [ parseInt(bits[1]), parseInt(bits[2]), parseInt(bits[3]) ];
			}
		},
		{
			re: /^(\w{2})(\w{2})(\w{2})$/,
			example: ['#00ff00', '336699'],
			process: function (bits){
				return [ parseInt(bits[1], 16), parseInt(bits[2], 16), parseInt(bits[3], 16) ];
			}
		},
		{
			re: /^(\w{1})(\w{1})(\w{1})$/,
			example: ['#fb0', 'f0f'],
			process: function (bits){
				return [ parseInt(bits[1] + bits[1], 16), parseInt(bits[2] + bits[2], 16), parseInt(bits[3] + bits[3], 16) ];
			}
		}
	];

	// search through the definitions to find a match
	for (var i = 0; i < color_defs.length; i++) {
		var re = color_defs[i].re;
		var processor = color_defs[i].process;
		var bits = re.exec(value);
		if (bits) {
			channels = processor(bits);
			this.r = channels[0];
			this.g = channels[1];
			this.b = channels[2];
			this.ok = true;
		}
	}

	// validate/cleanup values
	this.r = (this.r < 0 || isNaN(this.r)) ? 0 : ((this.r > 255) ? 255 : this.r);
	this.g = (this.g < 0 || isNaN(this.g)) ? 0 : ((this.g > 255) ? 255 : this.g);
	this.b = (this.b < 0 || isNaN(this.b)) ? 0 : ((this.b > 255) ? 255 : this.b);

	// some getters
	this.toRGB = function () {
		return 'rgb(' + this.r + ', ' + this.g + ', ' + this.b + ')';
	}
	this.toHex = function () {
		var r = this.r.toString(16);
		var g = this.g.toString(16);
		var b = this.b.toString(16);
		if (r.length == 1) r = '0' + r;
		if (g.length == 1) g = '0' + g;
		if (b.length == 1) b = '0' + b;
		return '#' + r + g + b;
	}
}
