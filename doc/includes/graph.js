/*
 NetworkParser
 Copyright (c) 2011 - 2014, Stefan Lindel
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
Pos = function(x, y, id) {this.x = x || 0; this.y = y || 0; if(id){this.id = id;} }

/* Info */
Info = function(info, parent, edge) { 
	this.property = info.property; 
	this.cardinality = info.cardinality; 
	this.id = info.id; 
	this.typ = "Info";
	this.x = this.y = this.width = this.height = 0;
	this.center=new Pos();
	this.custom = false;
	this._parent = parent;
	this.edge = edge;
	this.isdraggable = true;
};

Line = function(source, target, line, style) {this.source = source; this.target = target; this.line = line; this.style = style;}
Line.Format={SOLID:"SOLID", DOTTED:"DOTTED"};
/* Options */
Options = function(){
	this.canvasid = null;
	this._parent = null;
	this._subgraphs = [];
	this.raster = false;
	this.display = "svg";
	this.font={"font-size":"10px", "font-family": "Verdana"};
	this.layout= {name:"Dagre", rank:"TB", nodesep:10}	// Dagre TB, LR
	this.CardinalityInfo = true;
	this.PropertyInfo = true;
	this.buttons = [];	// ["HTML", "SVG", "CANVAS", "PNG", "PDF"]
}

/* Node */
GraphNode = function(id) {
	this.init();
	this.typ = "node";
	this.id = id;
}
GraphNode.prototype.init = function() {
	this.x = this.y = this.width = this.height=0;
	this.edges = [];
	this.attributes = [];
	this.methods = [];
	this.RIGHT = this.LEFT = this.UP = this.DOWN=0;
	this.isdraggable = true;
};

GraphNode.prototype.removeFromBoard = function(board){
	if(this.htmlNode){
		board.removeChild(this.htmlNode);
		this.htmlNode = null;
	}
};
GraphNode.prototype.getX = function(){
	if(this._parent){
		return this.x + this._parent.getX();
	}
	return this.x;
};
GraphNode.prototype.getY = function(){
	if(this._parent){
		return this.y + this._parent.getY();
	}
	return this.y;
};
GraphNode.prototype.getRoot = function() {
	if(this._parent){
		return this._parent.getRoot();
	}
	return this;
}

/* Graph */
Graph = function(json, options) {
	this.init();
	this.left=0;
	this.top=0;
	this.nodeCount=0;
	this.nodes = {};
	this.edges = [];
	this.typ = json.typ;
	this.initLayouts();
	if(json.info){
		this.info = json.info;
	}
	if(json.style){
		this.style = json.style;
	}
	this.options = this.merge(this.merge(new Options(), json.options), options, true);
	this._parent = this.options._parent;
	this.loader = new Loader(this);
	if((""+this.options.display).toLowerCase()=="html"){
		this.drawer = new HTMLDrawer();
		this.loader.init(true);
	}else{
		this.drawer = new SVGDrawer();
		this.options.display = "svg";
	}
	var layout = this.layouts[0];

	for(var i=0;i<this.layouts.length;i++){
		if(this.layouts[i]["name"] === this.options.layout.name.toLowerCase()){
			layout = this.layouts[i];
			break;
		}
	}
	this.layouter = layout.value;

	if(json.nodes) {
		for (var i = 0; i < json.nodes.length; i++) {
			this.addNode(json.nodes[i], i);
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
			edge.model = this;

			edge.target = this.getNode(e.target.id);
			edge.target.edges.push(edge);
			this.edges.push(edge);
		}
	}
	if(!this.options._parent){
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
Graph.prototype = Object_create(GraphNode.prototype);
Graph.prototype.initLayouts=function(){ this.layouts=[{name:"dagre", value:new DagreLayout()}];};
Graph.prototype.removeFromBoard = function(board){
	if(this.htmlNode){
		board.removeChild(this.htmlNode);
		this.htmlNode = null;
	}
	this.board = null;
};
Graph.prototype.merge = function(ref, src, full) {
	if(src){
		for(var i in src){
			if(i.charAt(0)=="_"){
				if(full){ref[i] = src[i];}
				continue;
			}
			if(typeof(src[i])=="object" && typeof(ref[i])=="object"){
				this.merge(ref[i], src[i])
			}else{
				ref[i] = src[i];
			}
		}
	}
	return ref;
};
Graph.prototype.copy = function(source, target){
	this.merge(target, source);
	if(source.width){target.startWidth = source.width;}
	if(source.height){node.startHeight = node.height;}
	return target;
};
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
			node.drawer.model = node;
			node.initGraph();
		}
		var html = this.drawer.getNode(node, true);
		if(html){
			var pos = this.getDimension(html);
			if(!node.startWidth){
				node.width=pos.x;
			}
			if(!node.startHeight){
				node.height=pos.y;
			}
		}
		if(node.typ=="objectdiagram" || node.typ=="classdiagram"){
			node.center = new Pos(node.x + (node.width / 2), node.y + (node.height / 2));
			node.board=null;
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
	var infoTxt = edge.getInfo(info);
	if(infoTxt.length > 0) {
		var html = this.drawer.createInfo(info, true, infoTxt);
		if(html){
			var pos = this.getDimension(html);
			info.width = pos.x;
			info.height = pos.y;
		}
	}
	return infoTxt;
};
Graph.prototype.clearBoard = function(){
	if(this.board){
		this.clearLines();
		for(var i in this.nodes) {
			var n=this.nodes[i];
			n.removeFromBoard(this.board);
			n.RIGHT = n.LEFT = n.UP = n.DOWN=0;
		}
		this.root.removeChild(this.board);
	}
	this.drawer.clearBoard();
};
Graph.prototype.getDimension = function(html){
	if(this._parent){
		return this._parent.getDimension(html);
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
	button.model = this;
	var that = this;
	bindEvent(button, "click", function(e){that.setTyp(e.innerHTML);});
	return button;
};
Graph.prototype.getNode = function(id, isSub) {
	if(this.nodes[id]) {
		return this.nodes[id];
	}
	for(var i = 0;i < this.options._subgraphs.length;i++){
		var r = this.options._subgraphs[i].getNode(id, true);
		if(r) {
			return r;
		}
	}
	if(!isSub){
		this.nodes[id] = new GraphNode(id);
		this.nodes[id]._parent = this;
		this.nodeCount++;
		return this.nodes[id];
	}
	return null;
};
Graph.prototype.addSubGraph = function(subgraph) {
	this.options._subgraphs.push(subgraph);
	if(this._parent) {
		this._parent.addSubGraph(subgraph);
	}
};

Graph.prototype.addNode = function(node, pos) {
	/* testing if node is already existing in the graph */
	node.typ = node.typ.toLowerCase();
	if(node.typ=="objectdiagram" || node.typ=="classdiagram") {
		if(!this.options) {
			return;
		}
		var options = new Options();
		options._parent = this;
		node = new Graph(node, options);
		this.addSubGraph(node);
	}else {
		node = this.copy(node, new GraphNode());
	}
	if(!(node.id)){
		node.id = node.typ+"_"+(pos || 0);
	}
	if(this.nodes[node.id] == undefined) {
		this.nodes[node.id] = node;
		node._parent = this;
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
Graph.prototype.calcLines = function(){
	var ownAssoc = [];
	for(var i in this.nodes) {
		this.nodes[i].RIGHT = this.nodes[i].LEFT = this.nodes[i].UP = this.nodes[i].DOWN=0;
	}
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
};
Graph.prototype.drawLines = function(){
	this.clearLines();
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
	
};
Graph.prototype.MinMax = function(node, min, max){
	max.x = Math.max(max.x,node.x+node.width+10);
	max.y=Math.max(max.y,node.y+node.height+10);
	min.x=Math.max(min.x,node.x);
	min.y=Math.max(min.y,node.y);
};
Graph.prototype.resize = function(){
	var min=new Pos();
	var max=new Pos(this.minSize.x, this.minSize.y);
	for (var i in this.nodes) {
		var node = this.nodes[i];
		this.moveToRaster(node);

		this.MinMax(node, min, max);
	}
	this.left = this.top = 0;

	this.calcLines();
	for(var i=0;i<this.edges.length;i++){
		var edge=this.edges[i];
		this.MinMax(edge.sourceInfo, min, max);
		this.MinMax(edge.targetInfo, min, max);
	}
	this.drawer.setSize(this.board, max.x, max.y);
	if(this.options.raster){
		this.drawRaster();
	}
	this.drawLines();
	return max;
};
Graph.prototype.drawRaster = function(){
	while(this.board.rasterElements.length>0){
		this.board.removeChild(this.board.rasterElements.pop());
	}
	var width = this.board.style.width.replace("px","");
	var height = this.board.style.height.replace("px","");
	for(var i=10;i<width;i+=10){
		var line = this.drawer.createLine(i, 0, i, height, null, "#ccc");
		line.className="lineRaster";
		this.board.rasterElements.push(line);
		this.board.appendChild(line);
	}
	for(var i=10;i<height;i+=10){
		var line = this.drawer.createLine(0, i, width, i, null, "#ccc");
		line.className="lineRaster";
		this.board.rasterElements.push(line);
		this.board.appendChild(line);
	}
};
Graph.prototype.drawGraph = function(width, height){
	this.minSize = new Pos(width, height);
	if(this.loader.abort && this.loader.images.length>0){
		return;
	}
	if(!this.board) {
		this.initGraph();
	}
	this.resize();

	for(var i in this.nodes) {
		var node = this.nodes[i];
		node.htmlNode = this.drawer.getNode(node, false);
		if(node.htmlNode){
			this.board.appendChild( node.htmlNode );
		}
	}
};
Graph.prototype.moveToRaster = function(node){
	if(this.left>0 || this.top > 0) {
		node.x += this.left;
		node.y = this.top;
	}
	if(this.options.raster){
		node.x = parseInt(node.x / 10) * 10;
		node.y = parseInt(node.y / 10) * 10;
		if(node.htmlNode){
			this.drawer.setPos(node.htmlNode, node.x, node.y);
		}
	}

}

Graph.prototype.layout = function(minwidth, minHeight){
	this.layouter.layout(this, Math.max(minwidth || 0, 100), Math.max(minHeight || 0, 100));
}
Graph.prototype.layouting = function(){
	this.initGraph();
	this.layout(this.minSize.x, this.minSize.y);
}
//				######################################################### DRAG AND DROP #########################################################
Graph.prototype.initDragAndDrop = function(){
	this.objDrag = null;
	this.mouse = new Pos();
	this.offset= new Pos();
	this.startObj= new Pos();
	var that = this;
	bindEvent(this.board, "mousemove", function(e){that.doDrag(e);});
	bindEvent(this.board, "mouseup", function(e){that.stopDrag(e);});
	bindEvent(this.board, "mouseout", function(e){that.stopDrag(e);});
};
Graph.prototype.addNodeLister = function(element, node){
	var that = this;
	element.node = node;
	bindEvent(element, "mousedown", function(e){that.startDrag(e);});
};
Graph.prototype.showinfo = function(event){
	var objElem = event.currentTarget;
	var node=this.getGraphNode(objElem);
	if(node){
		var x = Math.round( objElem.style.left.substring(0,objElem.style.left.length-2) * 100)/100;
		var y = Math.round( objElem.style.top.substring(0,objElem.style.top.length-2) * 100)/100;
		node._parent.showInfoText("Box-Position: " + x + ":" + y);
	}
};
Graph.prototype.setSelectable = function(node, value) {
	if (node.nodeType == 1) {
		if(value){
			node.setAttribute("unselectable", value);
		}else{
			node.removeAttribute("unselectable");
		}
	}
	var child = node.firstChild;
	while (child) {
		this.setSelectable(child, value);
		child = child.nextSibling;
	}
};
Graph.prototype.getDragNode = function(node) {
	if(node.model){
		if(!node.model.isdraggable){
			return null;
		}
		return node;
	}else if(node.parentElement.model) {
		if(!node.parentElement.model.isdraggable){
			return null;
		}
		return node.parentElement;
	}
	return null;
}
Graph.prototype.startDrag = function(event) {
	var n = this.getDragNode(event.currentTarget);
	if(!n){
		return;
	}
	if(this.objDrag){
		return;
	}
	this.objDrag = n;
	var graph = this.objDrag.parentElement;
	if(graph) {
		for(var i=0;i<graph.children.length;i++) {
			this.setSelectable(graph.children[i], "on");
		}
	}
	this.offset.x = (IE) ? window.event.clientX : event.pageX;
	this.offset.y = (IE) ? window.event.clientY : event.pageY;
	this.startObj.x = this.objDrag.model.x;
	this.startObj.y = this.objDrag.model.y;
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
			if(this.objDrag.model){
				this.objDrag.model.x = x;
				this.objDrag.model.y = y;
				this.objDrag.model._parent.resize();
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
			this.setSelectable(graph.children[i], null);
		}
	}
	if(item.model){
		if(this.options.display=="svg"){
			if(item.getAttributeNS(null, "transform")){
				var pos = item.getAttributeNS(null, "transform").slice(10,-1).split(' ');
				item.model.x = item.model.x + Number(pos[0]);
				item.model.y = item.model.y + Number(pos[1]);
			}
			item.model.center = new Pos(item.model.x + (item.model.width / 2), item.model.y + (item.model.height / 2));
			
			this.board.removeChild(item);
			if(item.model.board) {
				item.model.board = null;
			}

			if(item.model.typ=="Info") {
				item.model.custom = true;
				item.model.edge.removeElement(item);
				var infoTxt = item.model.edge.getInfo(item.model);
				item.model.edge.addElement(this.board, this.drawer.createInfo(item.model, false, infoTxt));
			}else{
				item.model.htmlNode = this.drawer.getNode(item.model, false);
				if(item.model.htmlNode){
					this.board.appendChild( item.model.htmlNode );
				}
				for(var i=0;i<item.model.edges.length;i++){
					var edge = item.model.edges[i];
					edge.sourceInfo.custom = false;
					edge.targetInfo.custom = false;
				}
			}
		}
		item.model._parent.resize();
	}
};
Graph.prototype.redrawNode = function(node){
	this.board.removeChild(node.htmlNode);
	if(node.board) {
		node.board = null;
	}
	if(node.typ=="Info") {
		var infoTxt = node.edge.getInfo(node.node);
		node.edge.addElement(this.board, this.drawer.createInfo(node, false, infoTxt));
	}else{
		node.htmlNode = this.drawer.getNode(node, false);
		if(node.htmlNode){
			this.board.appendChild( node.htmlNode );
		}
	}
	node.center = new Pos(node.x + (node.width / 2), node.y + (node.height / 2));
	node._parent.resize();
};

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
Graph.prototype.setTyp = function(typ){
	if(typ=="HTML"){
		this.initDrawer(typ);
		this.loader.init(true);
		this.initGraph();
		this.drawGraph(0,0);
	}else if(typ=="SVG"){
		this.initDrawer(typ);
		this.initGraph();
		this.drawGraph(0,0);
	}else if(typ=="SVG-Export"){
		this.drawer = new SVGDrawer();
		this.initGraph();
		this.drawGraph(0,0);
		var size = this.resize();
		var img = document.createElement("img");
		img.src = "data:image/svg+xml;base64," + this.utf8_to_b64(this.serializeXmlNode(this.board));
		this.clearBoard();
		this.board = img;
		this.board.width = size.x;
		this.board.height = size.y;
		this.root.appendChild(img);
	}else if(typ=="CANVAS"){
		this.initDrawer(typ);
		this.initGraph();
		this.drawGraph(0,0)
	}else if(typ=="PNG"){
		var oldDrawer = this.drawer;
		this.drawer = new CanvasDrawer();
		this.loader.init(false);
		this.loader.oldDrawer = oldDrawer;
		this.initGraph();
		this.drawGraph(0,0);
		this.loader.resetDrawer();
	}else if(typ=="PDF"){
		this.ExportPDF();
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
Graph.prototype.ExportPDF = function () {
	var pdf = new jsPDF('l', 'px', 'a4');
	new svgConverter(this.board, pdf, {removeInvalid: false});
	pdf.save('Download.pdf');
};

Graph.prototype.ExportEPS = function () {
	var doc = new jsEPS({inverting:true});
	var converter = new svgConverter(this.board, doc);
	doc.save();
};

Graph.prototype.ExportPNG = function () {
	var image = new Image();
	image.src = 'data:image/svg+xml;base64,' + this.utf8_to_b64(this.serializeXmlNode(this.board));
	var that = this;
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
}
Graph.prototype.SaveAs = function (typ) {
	typ = typ.toLowerCase();
	if(typ=="svg") {
		this.Save("image/svg+xml", this.serializeXmlNode(this.board), "download.svg");
	}else if(typ=="html") {
		this.ExportHTML();
	}else if(typ=="htmlx") {
		this.ExportHTMLStandalone();
	}else if(typ=="png") {
		this.ExportPNG();
	}else if(typ=="pdf") {
		this.ExportPDF();
	}else if(typ=="eps") {
		this.ExportEPS();
	}
};
Graph.prototype.Save = function (typ, data, name) {
	var a = document.createElement("a");
	var url = window.URL.createObjectURL(new Blob([data], {type: typ}));
	a.href = url;
	a.download = name;
	a.click();
}

Graph.prototype.ExportHTML = function () {
	var json = this.getHTML();
	var data="<html><head>"+document.head.innerHTML.trim()+"</head><body><script>"
		+"new Graph("+JSON.stringify(json, null, "\t") +").layout();</script></body></html>";
	this.Save("text/json", data, "download.html");
}
Graph.prototype.ExportHTMLStandalone = function () {
	var json = this.getHTML();

	var data="<html><head>"+document.head.innerHTML.trim()+"</head><body><script>"
		+"new Graph("+JSON.stringify(json, null, "\t") +").layout();</script></body></html>";
	this.Save("text/json", data, "download.html");
}
Graph.prototype.getHTML = function () {
	var result = {};
	result.typ = this.typ;
	result.options = {};

	for (var key in this.options) {
		if(key.charAt(0)!="_" && this.options[key] != null) {
			result.options[key] = this.options[key];
		}
	}
	var items = [];
	var add = false;
	for (var i in this.nodes) {
		var n = this.nodes[i];
		var newNode = {typ:n.typ, id:n.id, x: n.x, y:n.y, width:n.width, height:n.height, style:n.style };
		if(n instanceof GraphNode) {
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
		}
		if(n instanceof Graph) {
			var sub = n.getHTML();
			this.copy(sub, newNode);
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
	return result;
};

//				######################################################### GraphLayout-Dagre #########################################################
DagreLayout = function() {};
DagreLayout.prototype.layout = function(graph, width, height) {
	this.graph = graph;
	
	this.g = new dagre.graphlib.Graph(graph.merge({directed:false}, this.graph.options.layout));
	this.g.setGraph({});
	this.g.setDefaultEdgeLabel(function() { return {}; });
	for (var i in this.graph.nodes) {
		var node = this.graph.nodes[i];
		this.g.setNode(node.id, {label: node.id, width:node.width, height:node.height, x:node.x, y:node.y});
		
	}
	for (var i = 0; i < this.graph.edges.length; i++) {
		var edges = this.graph.edges[i];
		this.g.setEdge(this.getRootNode(edges.source).id, this.getRootNode(edges.target).id);
	}

	dagre.layout(this.g);
	// Set the layouting back
	for (var i in this.graph.nodes) {
		var node = this.graph.nodes[i];
		var layoutNode = this.g.node(node.id);
		node.x = layoutNode.x - (node.width/2);
		node.y = layoutNode.y - (node.height/2);
	}
	this.graph.drawGraph(width, height);
}
DagreLayout.prototype.getRootNode = function(node, child) {
	if(node._parent){
		return this.getRootNode(node._parent, node);
	}
	if(!child){
		return node;
	}
	return child;
};
//				######################################################### Loader #########################################################
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
Loader.prototype.remove = function(img){this.images.remove(img);}
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

//				######################################################### LINES #########################################################
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
	var options = drawer.model.options;
	this.drawSourceText(board, drawer, options);
	if(this.info) {
		this.addElement(board, drawer.createInfo(this.infoPos, false, this.info));
	}
	this.drawTargetText(board, drawer, options);
};
Edge.prototype.drawSourceText = function(board, drawer, options){
	var infoTxt = this.getInfo(this.sourceInfo);
	if(infoTxt.length > 0 ){
		this.addElement(board, drawer.createInfo(this.sourceInfo, false, infoTxt));
	}
}
Edge.prototype.drawTargetText = function(board, drawer, options){
	var infoTxt = this.getInfo(this.targetInfo);
	if(infoTxt.length > 0 ){
		this.addElement(board, drawer.createInfo(this.targetInfo, false, infoTxt));
	}
}
Edge.prototype.endPos = function(){return this.path[this.path.length-1];}
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
Edge.prototype.getTarget = function(node, startNode){
	if(node instanceof Graph){
		if(node.status=="close") {
			return node;
		}
		return startNode;
	}
	return this.getTarget(node._parent, startNode);
};
Edge.prototype.calcCenterLine = function(){
	var divisor = (this.target.center.x - this.source.center.x);
	var sourcePos,targetPos;
	var edgePos = this.edgePosition() * 20;

	this.path = new Array();
	var source = this.getTarget(this.source, this.source), target = this.getTarget(this.target, this.target);
	if(divisor==0){
		if(this.source==this.target){
			/* OwnAssoc */
			return false;
		}
		// Must be UP_DOWN or DOWN_UP
		if(this.source.center.y<this.target.center.y){
			// UP_DOWN
			sourcePos = this.getCenterPosition(source, Edge.Position.DOWN, edgePos);
			targetPos = this.getCenterPosition(target, Edge.Position.UP, edgePos);
		}else{
			sourcePos = this.getCenterPosition(source, Edge.Position.UP, edgePos);
			targetPos = this.getCenterPosition(target, Edge.Position.DOWN, edgePos);
		}
	}else{
		this.m = (target.center.y - source.center.y) / divisor;
		this.n = source.center.y - (source.center.x * this.m);
		sourcePos = this.getPosition(this.m,this.n, source, target.center, edgePos);
		targetPos = this.getPosition(this.m,this.n, target, sourcePos, edgePos);
	}
	if(sourcePos&&targetPos){
		this.calcInfoPos( sourcePos, source, this.sourceInfo, edgePos);
		this.calcInfoPos( targetPos, target, this.targetInfo, edgePos);
		this.addEdgeToNode(source, sourcePos.id);
		this.addEdgeToNode(target, targetPos.id);
		this.path.push ( new Line(sourcePos, targetPos, this.lineStyle, this.style));
		this.infoPos = new Pos( (sourcePos.x + targetPos.x)/2, (sourcePos.y + targetPos.y)/2 );
	}
	return true;
};
Edge.prototype.getCenterPosition = function(node, pos, offset){
	offset = offset || 0;
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
Edge.prototype.getInfo = function(info){
	var infoTxt = "";
	var isCardinality = this.model.typ=="classdiagram" && this.model.options.CardinalityInfo;
	var isProperty = this.model.typ=="classdiagram" && this.model.options.PropertyInfo;

	if(isProperty && info.property){
		infoTxt = info.property;
	}
	if(isCardinality && info.cardinality){
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

		this.infoPos = new Pos( (sPos.x + tPos.x)/2, sPos.y );
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

		this.infoPos = new Pos( sPos.x , (sPos.y + tPos.y)/2 );
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
	if(node[list[id + 1]] == 0 || node[list[id + 1]] < node[list[id + 3]]) {
		node[list[id + 1]] ++;
		return list[id + 1];
	}
	node[list[id + 3]]++;
	return list[id + 3];
}
Edge.prototype.calcInfoPos = function(linePos, item, info, offset){
	// Manuell move the InfoTag
	offset = offset || 0;
	var spaceA = 20;
	var spaceB = 10;
	if(info.custom){
		return;
	}
	var newY = linePos.y;
	var newX = linePos.x;
	var yoffset = 0;
	if(linePos.id==Edge.Position.UP){
		newY = newY - info.height - offset - spaceA;
		if(this.m!=0){
			newX = (newY-this.n) / this.m + spaceB;
		}
	}else if(linePos.id==Edge.Position.DOWN){
		newY = newY + offset + spaceA;
		if(this.m!=0){
			newX = (newY-this.n) / this.m + spaceB;
		}
	}else if(linePos.id==Edge.Position.LEFT){
		newX = newX - info.width - offset - spaceA;
		if(this.m!=0){
			newY = (this.m * newX)+ this.n;
		}
	}else if(linePos.id==Edge.Position.RIGHT){
		newX += offset + spaceA;
		if(this.m!=0){
			newY = (this.m * newX)+ this.n;
		}
	}
	info.id = linePos.id;
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
		pos.push(new Pos(x + offset, y, Edge.Position.UP));
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

function bindEvent(el, eventName, eventHandler) {
	if (el.addEventListener){
		el.addEventListener(eventName, eventHandler, false); 
	} else if (el.attachEvent){
		el.attachEvent('on'+eventName, eventHandler);
	}
}
function RGBColor(value){
	this.ok = false;
	if(value=="none"){
		return;
	}
	var div = document.createElement("div");
	div.style.backgroundColor = value;
	document.body.appendChild(div);
	var computedColor = window.getComputedStyle(div).backgroundColor;
	// cleanup temporary div.
	document.body.removeChild(div);
	this.convert(computedColor);
};
RGBColor.prototype.convert = function(value){
	var regex = /rgb *\( *([0-9]{1,3}) *, *([0-9]{1,3}) *, *([0-9]{1,3}) *\)/;
	var values = regex.exec(value);
	this.r = parseInt(values[1]);
	this.g = parseInt(values[2]);
	this.b = parseInt(values[3]);
	this.ok = true;
};

RGBColor.prototype.toRGB = function () {
	return 'rgb(' + this.r + ', ' + this.g + ', ' + this.b + ')';
};
RGBColor.prototype.toHex = function () {
	return "#" 
	+ (this.r + 0x10000).toString(16).substring(3).toUpperCase() 
	+ (this.g + 0x10000).toString(16).substring(3).toUpperCase()
	+ (this.b + 0x10000).toString(16).substring(3).toUpperCase();
};
