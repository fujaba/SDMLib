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
Object_create = Object.create || function (o) {var F = function() {};F.prototype = o; return new F();};
Drawer = function(){};
Drawer.prototype.isShowRaster = function(){return false;}
Drawer.prototype.showInfoBox = function(){return false;}
Drawer.prototype.clearBoard = function(){};
Drawer.prototype.onLoadImage = function(){};
Drawer.prototype.onFinishImage = function(){};
Drawer.prototype.setPos = function(item, x, y){item.x = x;item.y = y;};
Drawer.prototype.createText = function(text){return document.createTextNode(text);}
Drawer.prototype.setSize = function(item, x, y){item.width = x;item.height = y;};
Drawer.prototype.createSubGraph = function(node, element){
	node.board = element;
	node.layoutCalculate();
};
Drawer.prototype.createObject= function(ns, tag){
	if(document.createElementNS){
		return document.createElementNS(ns, tag);
	}
	var e = document.createElement(tag);
	e.setAttribute('xmlns', ns);
	return e;
};
Drawer.prototype.getBoard = function(graph){
	if(graph.parent){
		return this.getBoard(graph.parent);
	}
	return graph.board;
};
Drawer.prototype.getNumber = function(num) {return Number(num.toString().replace("px",''));}

HTMLDrawer = function() {};
HTMLDrawer.prototype = Object_create(Drawer.prototype);
HTMLDrawer.prototype.showInfoBox = function(){return true;}
HTMLDrawer.prototype.isShowRaster = function(){return true;}
HTMLDrawer.prototype.setPos = function(item, x, y){item.style.left = x+"px";item.style.top = y+"px";};
HTMLDrawer.prototype.setSize = function(item, x, y){item.style.width = x+"px";item.style.height = y+"px";};
HTMLDrawer.prototype.createContainer = function(graph){
	this.graph = graph;	
	var board = document.createElement("div");
	board.className="Board";
	board.graph = graph;
	board.rasterElements=[];
	return board;
};
HTMLDrawer.prototype.createImage = function(model){
	var img = new Image();
	img.src = model.src;
	img.node = model;
	var add=true;
	if(model.width){
		img.width = model.width;
		add=false;
	}
	if(model.height){
		img.height = model.height;
		add=false;
	}
	if(add){
		this.graph.loader.appendImg(img);
	}
	return img;
};

HTMLDrawer.prototype.createCell = function(parent, tag){
	var tr = document.createElement('tr');
	var cell = document.createElement(tag);
	cell.style.fontSize=this.graph.options.font["font-size"];
	tr.appendChild(cell);
	parent.appendChild(tr);
	return cell;
};
HTMLDrawer.prototype.getHTMLNode = function(node, calculate){
	var htmlElement = document.createElement("div");
	htmlElement["isdraggable"]=true;
	var symbolLib = new SymbolLibary();
	if(node.typ=="patternobject") {
		htmlElement.className="patternElement";
	} else if(symbolLib.isSymbol(node)) {
		return symbolLib.draw(null, node, calculate);
	} else if(node.typ=="classdiagram") {
		htmlElement.className="classdiagram";
	} else if(node.typ=="objectdiagram") {
		htmlElement.className="objectdiagram";
	} else if(this.graph.typ.toLowerCase()=="objectdiagram") {
		htmlElement.className="objectElement";
	} else {
		htmlElement.className="classElement";
	}
	this.setPos(htmlElement, node.x, node.y);
	htmlElement.style.zIndex=5000;

	if(node.typ=="objectdiagram" || node.typ=="classdiagram"){
		this.createSubGraph(node, htmlElement);
		this.setSize(htmlElement, node.board.style.width, node.board.style.height);
		return htmlElement;
	}
	this.graph.addNodeLister(htmlElement, node);
	if(node.content_src){
		if(!node.content_img){
			node.content_img = {};
			node.content_img.src = node.content_src;
			node.content_img.width = node.content_width;
			node.content_img.height = node.content_height;
		}
		htmlElement.appendChild(this.createImage(node.content_img));
		return htmlElement;
	}
	if(node.content_html){
		htmlElement.innerHTML = node.content_html;return htmlElement;
	}
	if(node.content_plain){
		htmlElement.appendChild(this.createText(node.content_plain));return htmlElement;
	}
	var table = document.createElement('table');
	htmlElement.appendChild(table);
	var cell;
	table.border="0";
	if(node.head_src){
		cell = this.createCell(table, "td");
		if(!node.head_img){
			node.head_img = {};
			node.head_img.src = node.head_src;
			node.head_img.width = node.head_width;
			node.head_img.height = node.head_height;
		}
		cell.appendChild(this.createImage(node.head_img));
	}
	if(node.headinfo){
		cell = this.createCell(table, "td");
		cell.className="head";
		cell.innerHTML = node.headinfo;
	}
	if(this.graph.typ.toLowerCase()=="objectdiagram"){
		info = "<u>"+ node.id.charAt(0).toLowerCase() + node.id.slice(1) + "</u>";
	}else{
		info = node.id;
	}
	if(node.href){
		info = "<a href=\""+node.href+"\">" + info + "</a>";
	}
	cell = this.createCell(table, "th");
	cell.innerHTML = info;

	if(node.attributes){
		var first=true;
		for(var a = 0; a < node.attributes.length; a++){
			cell = this.createCell(table, "td");
			cell.innerHTML = node.attributes[a];
			if(!first){
				cell.className = 'attributes';
			}else{
				cell.className = 'attributes first';
				first=false;
			}
		}
	}
	if(node.methods){
		var first=true;
		for(var m=0;m < node.methods.length;m++){
			var method = node.methods[m];
			cell = this.createCell(table, "td");
			cell.innerHTML = node.methods[m];
			if(!first){
				cell.className = 'methods';
			}else{
				cell.className = 'methods first';
				first=false;
			}
		}
	}
	htmlElement.appendChild(table);
	htmlElement.node = node;
	node.htmlElement = htmlElement;
	return htmlElement;
};

HTMLDrawer.prototype.createInfo = function(item, calculate, text) {
	var info = document.createElement("div");
	info["isdraggable"]=true;
	info.className="EdgeInfo";
	info.style.fontSize = this.graph.options.font["font-size"];
	this.setPos(info, item.x, item.y);
	info.innerHTML = text;
	return info;
};

HTMLDrawer.prototype.createLine = function(x1, y1, x2, y2, lineStyle, style){
	if (x2 < x1 ){
		var temp = x1;
		x1 = x2;
		x2 = temp;
		temp = y1;
		y1 = y2;
		y2 = temp;
	}
	// Formula for the distance between two points
	// http://www.mathopenref.com/coorddist.html
	var length = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));

	var line = document.createElement("div");
	line.className="lineElement";
	line.style.width = length + "px";
	line.style.position = "absolute";
	line.style.zIndex = 42;
	line.style.borderBottomStyle= lineStyle;

	var angle = Math.atan((y1-y2)/(x1-x2));
	if(x1==x2){
		angle = Math.atan((y1-y2)/(x1-x2))*-1;
	}
	line.style.top = y1 + 0.5*length*Math.sin(angle) + "px";
	line.style.left = x1 - 0.5*length*(1 - Math.cos(angle)) + "px";
	line.style.transform="rotate("+angle+"rad)";
	line.style.MozTransform = line.style.WebkitTransform = line.style.OTransform= "rotate(" + angle + "rad)";
	return line;
};
HTMLDrawer.prototype.onLoadImage = function(event){
	var img = event.target;
	img.node.width = img.width;
	img.node.height = img.height;
	this.graph.loader.onLoad(img);
};
HTMLDrawer.prototype.onFinishImage = function(event){
	this.graph.layouting();
}
// ######################################################           SVG           ####################################################################################
SVGDrawer = function() {};
SVGDrawer.prototype = Object_create(Drawer.prototype);
SVGDrawer.prototype.addFontAttributes = function(node){
	if(this.graph && this.graph.options.font){
		for (var key in this.graph.options.font) {
			if(this.graph.options.font[key]){
				node.setAttribute(key, this.graph.options.font[key]);
			}
		}
	}
};
SVGDrawer.prototype.drawDef = function(){
	var def = this.createObject("http://www.w3.org/2000/svg", "defs");

	var child = this.createElement({tag:"filter", id:"drop-shadow"});
	child.appendChild( this.createElement({tag:"feGaussianBlur", in:"SourceAlpha", result:"blur-out", stdDeviation:2}));
	child.appendChild( this.createElement({tag:"feOffset", in:"blur-out", dx:2, dy:2}));
	child.appendChild( this.createElement({tag:"feBlend", in:"SourceGraphic", mode:"normal"}));
	def.appendChild( child );
	
	child = this.createElement({tag:"linearGradient", id:"reflect", x1:"0%", x2:"0%", y1:"50%", y2:"0%", spreadMethod:"reflect"});
	child.appendChild( this.createElement({tag:"stop", "stop-color":"#aaa",offset:"0%"}) );
	child.appendChild( this.createElement({tag:"stop", "stop-color":"#eee",offset:"100%"}) );
	def.appendChild( child );
	return def;

};
SVGDrawer.prototype.drawButton = function(action){
	var btn = this.createElement({tag:"g"});
	btn.appendChild( this.createElement({tag:"rect", rx:8, x: 86, y:8, width:60, height:28, stroke:"#000", filter:"url(#drop-shadow)", class:"saveBtn"}));
	btn.appendChild( this.createElement({tag:"text", x:98, y:26, fill:"black", value:"Save", class:"hand"}));
	btn.onclick = action;
	btn.close = function(){};
	return btn;
};
SVGDrawer.prototype.drawComboBox = function(elements, activText, action){
	var g = this.createElement({tag:"g"});
	g.tool={};
	g.tool.x = 0;
	g.tool.y = 8;
	g.status="close";
	g.appendChild( this.createElement({tag:"rect", rx:0, x: g.tool.x, y: g.tool.y, width:60, height:28, stroke:"#000", fill:"none"}));
	g.appendChild( this.createElement({tag:"rect", rx:2, x: g.tool.x+60, y: g.tool.y, width:20, height:28, stroke:"#000", class:"saveBtn"}));
	g.appendChild( this.createElement({tag:"path", style:"fill:#000000;stroke:#000000;stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1;fill-opacity:1",
									d:"m 65,"+(g.tool.y+13)+" 10,0 L 70,"+(g.tool.y+20)+" z"}));
	g.tool.minheight = 28;
	g.tool.maxheight = 28;
	g.tool.width = 80;
	if(elements){
		var choicebox = this.createElement({tag:"g"});
		var h = elements.length * 24+6;
		choicebox.appendChild( this.createElement({tag:"rect", rx:0, x: 0, y: g.tool.y+28, width:60, height:h, stroke:"#000", fill:"#fff", opacity:"0.7"}));
		g.tool.maxheight = h + g.tool.minheight;
		
		g.elements=elements;
		g.activ = this.createElement({tag:"text", "text-anchor":"left", "width": 60, "x": 10, "y": g.tool.y+20, value: activText})
		g.appendChild(g.activ);
		var y = 46+g.tool.y;
		var yr = 28+g.tool.y;
		for(var e=0;e<elements.length;e++){
			var element = elements[e];
			choicebox.appendChild(this.createElement({tag:"text", "text-anchor":"left", "width": 60, "x": 10, "y": y, value:element}));
			var item = choicebox.appendChild( this.createElement({tag:"rect", rx:0, x: 0, y: yr, width:60, height:24, stroke:"none", class:"selection"}));
			item.value = element;
			if(action) {
				item.onclick = action;
			} else {
				item.onclick = (function (event) {
					g.activ.textContent = event.currentTarget.value;
				});
			}
			y += 26;
			yr += 26;
		}
		g.choicebox = choicebox;
	}
	
	g.onclick = (function (event) {
		if(g.status=="close"){
			g.open();
		}else{
			g.close();
		}
	});
	
	g.close = function(){
		if(g.status=="open"){
			this.removeChild(this.choicebox);
		}
		this.status="close";
		this.tool.height = this.tool.minheight;
	};
	g.open = function(){
		if(g.status=="close"){
			this.appendChild( this.choicebox );
		}
		this.status="open";
		this.tool.height = this.tool.maxheight;
	};
	g.close();
	return g;
};
SVGDrawer.prototype.removeToolItems = function(board) {
	for(var i=0;i<this.toolitems.length;i++){
		this.toolitems[i].close();
		board.removeChild( this.toolitems[i] );
	}
}
SVGDrawer.prototype.showToolItems = function(board) {
	for(var i=0;i<this.toolitems.length;i++){
		board.appendChild( this.toolitems[i] );
	}
};

SVGDrawer.prototype.isInTool = function(x, y, ox, oy) {
	for(var i=0;i<this.toolitems.length;i++){
		var g = this.toolitems[i];
		if(x>=(g.tool.x+ox) && x<=(g.tool.x+g.tool.width+ox) && y>=(g.tool.y+oy) && y<=(g.tool.y+g.tool.height+oy)) {
			return true;
		}
	}
	return false;
};

SVGDrawer.prototype.createContainer = function(graph){
	this.graph = graph;
	var board = this.createElement({tag:"svg", xmlns:"http://www.w3.org/2000/svg", "xmlns:svg":"http://www.w3.org/2000/svg", "xmlns:xlink":"http://www.w3.org/1999/xlink"});

	var that = this;
	board.appendChild( this.drawDef() );
	this.toolitems=[];
	this.toolitems.push(this.drawComboBox(["HTML", "SVG", "PNG", "PDF"], "Save", (function (e) {that.removeToolItems(board);that.graph.SaveAs(e.currentTarget.value);})));

	board.rasterElements=[];
	board.graph = graph;

	board.saveShow=false;
	board.onmouseover = (function (event) {
		that.showToolItems(board);
	});
	board.onmouseout = (function (event) {
		if(!that.isInTool(event.x, event.y, board.offsetLeft, board.offsetTop)){
			that.removeToolItems(board);
		}
		});
	return board;
};
SVGDrawer.prototype.setSize = function(item, x, y){
	x = this.getNumber(x);
	y = this.getNumber(y);
	item.setAttribute("width", Math.ceil(x));
	item.setAttribute("height", Math.ceil(y));
	item.style.width=Math.ceil(x);
	item.style.height=Math.ceil(y);
};

SVGDrawer.prototype.getWidth = function(label, calculate){
	var text = this.createObject("http://www.w3.org/2000/svg", "text");
	text.appendChild(document.createTextNode(label));
	this.addFontAttributes(text);

	text.setAttribute("width", "5px");
	var board = this.getBoard(this.graph);
	board.appendChild(text);
	var width = text.getBoundingClientRect().width;
	board.removeChild(text);
	return width;
}
SVGDrawer.prototype.getHTMLNode = function(node, calculate){
	var symbolLib = new SymbolLibary();
	if(symbolLib.isSymbol(node)) {
		return symbolLib.draw(this, node, calculate);
	}
	if(node.content_src){
		return this.createElement({tag:"image", height: node.height, width: node.width, content_src: node.content_src, isdraggable:true});
	}
	var group = this.createElement({tag:"g", "class":"draggable", isdraggable:true});
	if(node.content_svg){
		group.setAttribute('transform', "translate("+node.x+" "+node.y+")");
		group.innerHTML = node.content_svg;return group;
	}
	group.model = node;
	if(node.typ=="objectdiagram" || node.typ=="classdiagram"){
		this.createSubGraph(node, group);
		
		var width = this.getNumber(node.board.style.width);
		var height = this.getNumber(node.board.style.height);
		if(node.style && node.style.toLowerCase()=="nac"){
			group.appendChild(this.createGroup(node, symbolLib.drawStop(node)));
		}
		
		this.graph.addNodeLister(group, node);
		group.appendChild( this.createElement({tag:"rect", "width":width, "height":height, "fill":"none", "strokeWidth":"1px", "stroke":this.getColor(node.style, "#CCC"), "x":node.getX(), "y":node.getY()}));
		this.setSize(group, width, height);
		return group;
	}

	if(node.content_plain){
		var text = this.createElement({tag:"text", "text-anchor":"left", "x": (node.x + 10), "y":y});
		this.addFontAttributes(text);
		text.appendChild( document.createTextNode(node.content_plain) );
		return text;
	}

	var width=0;
	var height=40;
	var textWidth;

	if(this.graph.typ.toLowerCase()=="objectdiagram"){
		textWidth = this.getWidth(node.id.charAt(0).toLowerCase() + node.id.slice(1));
	}else{
		textWidth = this.getWidth(node.id);
	}
	width = Math.max(width, textWidth);
	if(node.attributes && node.attributes.length > 0 ){
		height = height + node.attributes.length*25;
		for(var a=0; a<node.attributes.length;a++){
			var attribute = node.attributes[a];
			width = Math.max(width, this.getWidth(attribute));
		}
	}else{
		height += 20;
	}
	
	if(node.methods && node.methods.length > 0){
		height = height + node.methods.length*25;
		for(var m=0; m<node.methods.length;m++){
			var method = node.methods[m];
			width = Math.max(width, this.getWidth(method));
		}
	} 
	width += 20;

	var y = node.getY();
	var x = node.getX();

	this.graph.addNodeLister(group, node);
	var rect = {tag:"rect", "width":width, "height":height, "x":x, "y":y, "fill":"none", class:"draggable"};
	var typ = node.typ.toLowerCase();
	if(typ=="patternobject") {
		rect["fill"] = "lightblue";
	}
	var strokeColor = this.getColor(node["style"]);
	rect["stroke"] = strokeColor;
	group.appendChild( this.createElement(rect) );

	var text = this.createElement({tag:"text", "text-anchor":"right", "x":x+width/2-textWidth/2, "y":y+20, "width":textWidth});

	if(this.graph.typ.toLowerCase()=="objectdiagram"){
		text.setAttribute("text-decoration", "underline");
		text.appendChild(document.createTextNode(node.id.charAt(0).toLowerCase() + node.id.slice(1)));
	}else{
		text.appendChild(document.createTextNode(node.id));
	}

	group.appendChild(text);
	group.appendChild( this.createElement({tag:"line", x1:x, y1:y + 30, x2: x + width, y2: y + 30, stroke:strokeColor}) );
	y += 50;

	if(node.attributes){
		for(var a=0;a<node.attributes.length;a++){
			var attribute = node.attributes[a];
			group.appendChild(this.createElement({tag:"text", "text-anchor":"left", "width": width, "x":(x+10), "y": y, value:attribute}));
			y += 20;
		}
		if(node.attributes.length>0) {
			y -= 10;
		}
	}
	if(node.methods && node.methods.length > 0){
		group.appendChild( this.createElement({tag:"line", x1:x, y1: y, x2: x + width, y2: y, stroke:"#000"}) );
		y+=20;
		for(var m=0;m<node.methods.length;m++){
			var method = node.methods[m];
			group.appendChild(this.createElement({tag:"text", "text-anchor":"left", "width": width, "x": x + 10, "y": y, value:method}));
			y += 20;
		}
	}
	return group;
};

SVGDrawer.prototype.createElement = function(node){
	var element = this.createObject("http://www.w3.org/2000/svg", node.tag);
	if(node.tag.toLowerCase()=="path"){
		element.setAttribute('fill', "rgb(255, 255, 255)");
		//element.setAttribute('style', "stroke:#000;");
	}
	this.addFontAttributes(element);

	for (var key in node) {
		key = key.toLowerCase();
		if(key=='tag')continue;
		if(key=='content_src'&& node.tag.toLowerCase()=="image") continue;
		if(key=='value'&& node.tag.toLowerCase()=="text") continue;
		if(key.indexOf("-")>=0){
			element.style[key] = node[key];
		}else if(node[key] != null) {
			element.setAttribute(key, node[key]);
			element[key] = node[key];
		}
	}
	if(node.tag.toLowerCase()=="text" && node.value){
		element.appendChild(document.createTextNode(node.value));
	}
	if(node.tag.toLowerCase()=="image"){
		element.setAttribute('xmlns:xlink', "http://www.w3.org/1999/xlink");
		element.setAttributeNS("http://www.w3.org/1999/xlink", 'href',node["content_src"]);
	}
	return element;
};

SVGDrawer.prototype.createInfo = function(item, calculate, text) {
	var items = text.split("\n");
	if(!calculate && items.length>1){
		var group = this.createElement({tag:"g", class:"draggable", isdraggable:true});
		for(var i = 0;i<items.length;i++) {
			var child = this.createElement({tag:"text", "text-anchor":"left", "x": item.x, "y": item.y+(item.size.y*i)});
			child.appendChild(document.createTextNode(items[i]));
			group.appendChild(child);
		}
		this.graph.addNodeLister(group, item);
		return group;
	}
	return this.createElement({tag:"text", "text-anchor":"left", "x": item.x, "y": item.y, value:text, isdraggable:true});
};
SVGDrawer.prototype.getColor = function(style, defaultColor) {
	if(style) {
		if(style.toLowerCase()=="create") {
			return "#008000";
		}
		if(style.toLowerCase()=="nac") {
			return "#FE3E3E";
		}
	}
	if(defaultColor) {
		return defaultColor;
	}
	return "#000";
}
SVGDrawer.prototype.createLine = function(x1, y1, x2, y2, lineStyle, style){
	var line = this.createElement({tag:"line", 'x1': x1, 'y1': y1, 'x2': x2, 'y2': y2});
	line.setAttribute("stroke",this.getColor(style));

	if(style && style.toLowerCase()=="dotted"){
		line.setAttribute("stroke-miterlimit",4);
		line.setAttribute("stroke-dasharray","1,1");
	}
	return line;
};
SVGDrawer.prototype.createGroup = function(node, group){
	var entity = document.createElementNS("http://www.w3.org/2000/svg", "g");
	if(group.scale){
		entity.setAttribute('transform', "translate("+group.x+" "+group.y+") scale("+group.scale+")");
	}else{
		entity.setAttribute('transform', "translate("+group.x+" "+group.y+")");
	}
	entity.setAttribute("height", group.height);
	entity.setAttribute("width", group.width);
	for (var i = 0; i < group.items.length; ++i){
		entity.appendChild( this.createElement( group.items[i] ) );
	}
	return entity;
};

// ######################################################           CANVAS           ####################################################################################
CanvasDrawer = function() {};
CanvasDrawer.prototype = Object_create(Drawer.prototype);
CanvasDrawer.prototype.clearBoard = function(){
	if( !this.graph ){
		return;
	}
	var canvas = this.graph.board;
	var context = canvas.getContext('2d');
	context.clearRect(0, 0, canvas.width, canvas.height);
};
CanvasDrawer.prototype.createContainer = function(graph){
	this.graph = graph;	
	var board = document.createElement("canvas");
	board.rasterElements=[];
	board.graph = graph;
	return board;
};
CanvasDrawer.prototype.getWidth = function(text){
	var context = this.graph.board.getContext('2d');
	context.font = this.graph.options.font["font-size"]+" "+this.graph.options.font["font-family"];
	var metrics = context.measureText(text);
	return metrics.width;

};
CanvasDrawer.prototype.getHTMLNode = function(node, calculate){
	var canvas = this.graph.board;

	// Calculate Height
	var width=0;
	var height=20;
	if(this.graph.typ=="objectdiagram"){
		width = Math.max(width, this.getWidth(node.id.charAt(0).toLowerCase() + node.id.slice(1)));
	}else{
		width = Math.max(width, this.getWidth(node.id));
	}
	if(node.attributes){
		height = height + node.attributes.length*20;
		for(var a=0;a<node.attributes.length;a++){
			var attribute = node.attributes[a];
			width = Math.max(width, this.getWidth(attribute));
		}
	}
	height += 10;
	width += 20;
	if(calculate){
		if(!node.startWidth){
			node.width = width;
		}
		if(!node.startHeight){
			node.height=height;
		}
		return null;
	}

	var textwidth=node.width-10;
	var context = canvas.getContext('2d');
	if(node.content_src){
		var img = document.createElement("img");
		img.src = node.content_src;
		img.node = node;
		this.graph.loader.appendImg(img);
		return null;
	}
	if(node.content_plain){
		context.font = this.graph.options.fontSize+"px Arial";
		context.fillText(node.content_plain, node.x, node.y);
		return null;
	}

	context.beginPath();
	context.rect(node.x, node.y, node.width, node.height);
	context.lineWidth = 1;
	context.strokeStyle = 'black';
	context.stroke();

	this.createLine(node.x, node.y+20, node.x + node.width, node.y+20);

	var context = canvas.getContext('2d');
	context.font = this.graph.options.fontSize+"px Arial";
	var text="";
	if(this.graph.typ=="objectdiagram"){
		text = node.id.charAt(0).toLowerCase() + node.id.slice(1);
		var start = node.x + (node.width - this.getWidth(text))/2;
		this.createLine(start, node.y+16, start + this.getWidth(text), node.y+16);
	}else{
		text = node.id;
	}
	context.fillText(text, node.x + (node.width - this.getWidth(text))/2, node.y + 15);

	if(node.attributes){
		var y = node.y+40;
		for(var a=0;a<node.attributes.length;a++){
			var attribute = node.attributes[a];
			var context = canvas.getContext('2d');
			context.font = this.graph.options.fontSize+"px Arial";
			context.fillText(attribute, node.x + 10, y);
			y += 20;
		}
	}
	return null;
};
CanvasDrawer.prototype.createInfo = function(item, calculate, text) {
	return null;
};
CanvasDrawer.prototype.createLine = function(x1, y1, x2, y2, style){
	var canvas = this.graph.board;
	var context = canvas.getContext('2d');
	if(style.toLowerCase()=="dotted"){
		context.setLineDash([1,2]);
	}
	context.moveTo(x1, y1);
	context.lineTo(x2, y2);
	context.stroke();
	return null;
};

CanvasDrawer.prototype.onLoadImage = function(event){
	var img = event.target;
	
	var canvas = this.graph.board;
	var context = canvas.getContext('2d');
	context.drawImage(img, img.node.x, img.node.y);
	this.graph.loader.remove(img);
	this.graph.root.removeChild(img);
	this.graph.loader.resetDrawer();
};
CanvasDrawer.prototype.onFinishImage = function(event){
	try{
		var img = document.createElement("img");
		img.src = this.graph.board.toDataURL();
		this.graph.clearBoard();
		this.graph.board = img;
		this.graph.root.appendChild(img);
	} catch (e) {
		this.graph.clearBoard();
		this.graph.drawer = this.oldDrawer;
		this.graph.drawGraph(0,0);
		alert("Browser nicht unterstuetzt");
	}
};
// Example Items
// {tag:"path", d:""}
// {tag:"rect", width:46, height:34}
// {tag:"ellipse", width:23, height:4}
// {tag:"line", x1:650, y1:-286, x2:650, y2:-252}
// {tag:"circle", r:5, x:12, y:0}
// {tag:"image", height: 30, width: 50, content_src: hallo}
// {tag:"text", "text-anchor":"left", x:"10"}

SymbolLibary = function(){};
SymbolLibary.prototype.upFirstChar = function(txt)
{
	return txt.charAt(0).toUpperCase() + txt.slice(1).toLowerCase();
};

SymbolLibary.prototype.isSymbol = function(node){
	var fn = this["draw" + this.upFirstChar(node.typ)];
	return typeof fn === "function";
};
SymbolLibary.prototype.draw = function(drawer, node, calculate){
	var fn = this["draw" + this.upFirstChar(node.typ)];
	if(typeof fn === "function"){
		var group = fn.apply(this, [node, calculate]);
		if( !drawer){
			drawer = new SVGDrawer();
			var board = drawer.createContainer(null);
			var element = drawer.createGroup(node, group);
			board.appendChild(element);
			return board;
		}
		return drawer.createGroup(node, group);
	}
};
SymbolLibary.prototype.drawSmily = function(node, calculte){
	return {
		x:0,
		y:0,
		width:100,
		height:100,
		items:[
			{tag:"path", d:"m 49.5002 25.0001a 24.5001 24.5000 0 1 1-49.0001 0 24.5001 24.5000 0 1 1 49.0001 0z"},
			{tag:"path", d:"m 8.6239 30.9175c 15.9633 20 32.1560 0.3211 32.1560 0.3211"},
			{tag:"path", d:"m 19.6330 19.6789a 1.7431 2.5229 0 1 1-3.4862 0 1.7431 2.5229 0 1 1 3.4862 0z"},
			{tag:"path", d:"m 33.4862 19.6789a 1.7431 2.5229 0 1 1-3.4862 0 1.7431 2.5229 0 1 1 3.4862 0z"},
			{tag:"path", d:"m 6.0550 31.0091c 3.3945 0.9175 4.0367-2.2017 4.0367-2.2017"},
			{tag:"path", d:"m 43.5780 31.3761c-3.3945 0.9175-4.0367-2.2017-4.0367-2.2017"}
		]};
};
SymbolLibary.prototype.drawDatabase = function(node, calculte){
	return {
		x:0,
		y:0,
		width:100,
		height:100,
		items:[
			{tag:"path", d:"M 650-252a 16 4 0 0 0 45 0"},
			{tag:"rect", width:46, height:34},
			{tag:"ellipse", width:23, height:4},
			{tag:"line", x1:650, y1:-286, x2:650, y2:-252},
			{tag:"line", x1:696, y1:-286, x2:696, y2:-252},
			{tag:"rect", width:46,height:42}
		]};
};
SymbolLibary.prototype.drawLetter = function(node, calculte){
	return {
		x:0,
		y:0,
		width:100,
		height:35,
		items:[
			{tag:"path", d:"m 1 1 98 0 0 48-98 0z"},
			{tag:"path", d:"m 1.2684 1.4855 48.7259 23.3589 48.6202-23.676"}
		]};
};

SymbolLibary.prototype.drawMobilphone = function(node, calculte){
	return {
		x:0,
		y:0,
		width:25,
		height:50,
		items:[
			{tag:"path", d:"m 4.1937 0.5 15.6127 0c 2.0463 0 3.6937 1.6474 3.6937 3.6936l 0 41.6127c 0 2.0463-1.6474 3.6937-3.6937 3.6937l-15.6127 0c-2.0463 0-3.6937-1.6474-3.6937-3.6937l 0-41.6127c 0-2.0462 1.6474-3.6936 3.6937-3.6936z"},
			{tag:"path", d:"m 12.5 2.7338a 0.5 0.5 0 1 1-1 0 0.5 0.5 0 1 1 1 0z"},
			{tag:"path", d:"m 14 45.6882a 2 2.0000 0 1 1-4 0 2 2.0000 0 1 1 4 0z"},
			{tag:"path", d:"m 8.3516 5.0581 7.2969 0"},
			{tag:"path", d:"m 1.6352 7.5455 20.7297 0 0 34.0796-20.7297 0z"},
		]};
};
SymbolLibary.prototype.drawWall = function(node, calculte){
	return {
		x:0,
		y:0,
		width:25,
		height:50,
		items:[
			{tag:"path", d:"m 26.5000 45.9384-5.0389 3.5616-20.9610-9.0435 0.0000-36.3952 5.0389-3.5613 20.9611 9.0437z"},
			{tag:"path", d:"m 2.7070 11.4274 18.3409 7.9133m-14.4589-12.5655 0 6.3473m 8.1631 21.7364 0 6.3472m-8.6393-9.9876 0 6.3472m 4.0923-10.6702 0 6.3473m 4.7743-10.2152 0 6.3473m-8.8666-10.2152 0 6.3472m 4.7743-10.2151 0 6.3472m-7.9572 14.4578 18.3409 7.9132m-18.3409-13.9132 18.3409 7.9132m-18.3409-13.9133 18.3409 7.9133m-18.3409-13.9133 18.3409 7.9132m-0.0000-13.0532-0.0001 34.0433m-18.2251-41.8406 18.2998 7.9024m 0 0.1115 4.9978-3.5723"}
		]};
};
SymbolLibary.prototype.drawActor = function(node, calculte){
	return {
		x:10,
		y:10,
		width:25,
		height:50,
		items:[
			{tag:"line", x1:12,y1:5,x2:12,y2:25},
			{tag:"circle", r:5, cx:12, cy:0},
			{tag:"line", x1:0, y1:13, x2:25, y2:13},
			{tag:"line", x1:12, y1:25, x2:5, y2:34},
			{tag:"line", x1:12, y1:25, x2:20, y2:34}
		]};
};
SymbolLibary.prototype.drawLamp = function(node, calculte){
	return {
		x:10,
		y:10,
		width:25,
		height:50,
		items:[
			{tag:"path", d:"m 22.4676 10.5797c-6.5690 0-11.8905 5.1708-11.8905 11.5437 0 2.3507 0.7376 4.538 1.9817 6.3616 2.0562 3.9241 4.3637 5.6306 4.4198 10.4001l 11.1459 0c 0.1160-4.9336 2.5455-6.7664 4.4319-10.4001 1.3930-1.5069 1.7799-4.4684 1.8016-6.3616 0-6.3729-5.3215-11.5437-11.8905-11.5437z"},
			{tag:"path", d:"m 18.4085 40.0784 8.0294 0c 0.5820 0 1.0505 0.4685 1.0505 1.0504 0 0.582-0.4685 1.0505-1.0505 1.0505l-8.0294 0c-0.5820 0-1.0505-0.4685-1.0505-1.0505 0-0.5819 0.4685-1.0504 1.0505-1.0504z"},
			{tag:"path", d:"m 18.4085 42.7311 8.0294 0c 0.5820 0 1.0505 0.4685 1.0505 1.0504 0 0.582-0.4685 1.0505-1.0505 1.0505l-8.0294 0c-0.5820 0-1.0505-0.4685-1.0505-1.0505 0-0.5819 0.4685-1.0504 1.0505-1.0504z"},
			{tag:"path", d:"m 18.4411 45.2823 8.0294 0c 0.5820 0 1.0505 0.4685 1.0505 1.0505 0 0.582-0.4685 1.0505-1.0505 1.0505l-8.0294 0c-0.5820 0-1.0505-0.4685-1.0505-1.0505 0-0.582 0.4685-1.0505 1.0505-1.0505z"},
			{tag:"path", d:"m 19.4727 48.0741c 0.3690 0.8074 1.0610 1.3087 1.8885 1.7116 0.6333 0.3084 1.4623 0.262 2.1164 0 0.7971-0.3192 1.4109-0.7966 1.8559-1.7762z"},
			{tag:"path", d:"m 5.9483 37.4973 4.1544-4.0548c 0.3042-0.2969 0.7902-0.2931 1.0897 0.0084 0.2995 0.3016 0.2958 0.7833-0.0084 1.0802l-4.1544 4.0548c-0.3042 0.2969-0.7902 0.2931-1.0897-0.0085-0.2995-0.3016-0.2958-0.7833 0.0084-1.0802z"},
			{tag:"path", d:"m 39.0558 37.5618-4.1544-4.0548c-0.3042-0.2969-0.7902-0.2931-1.0897 0.0085-0.2995 0.3016-0.2958 0.7833 0.0084 1.0802l 4.1544 4.0548c 0.3042 0.2969 0.7902 0.2931 1.0897-0.0085 0.2995-0.3016 0.2958-0.7833-0.0084-1.0802z"},
			{tag:"path", d:"m 37.886 22.9798 5.8406-0.0467c 0.4233-0.0034 0.7616-0.3469 0.7584-0.7703-0.0032-0.4233-0.3465-0.7614-0.7698-0.7580l-5.8406 0.0467c-0.4233 0.0034-0.7616 0.3469-0.7584 0.7702 0.0032 0.4234 0.3465 0.7615 0.7698 0.7581z"},
			{tag:"path", d:"m 1.2884 22.9797 5.8406-0.0467c 0.4233-0.0034 0.7616-0.3469 0.7584-0.7702-0.0032-0.4233-0.3465-0.7614-0.7698-0.7580l-5.8406 0.0467c-0.4233 0.0034-0.7616 0.3469-0.7584 0.7702 0.0032 0.4233 0.3465 0.7614 0.7698 0.7580z"},
			{tag:"path", d:"m 34.7476 11.2245 4.0877-4.1204c 0.2994-0.3018 0.2956-0.7839-0.0084-1.0810-0.3040-0.2971-0.7898-0.2933-1.0892 0.0084l-4.0877 4.1204c-0.2994 0.3018-0.2956 0.7839 0.0084 1.0810 0.3040 0.2971 0.7898 0.2933 1.0892-0.0084z"},
			{tag:"path", d:"m 11.2494 9.9815-4.1544-4.0548c-0.3042-0.2969-0.7902-0.2931-1.0897 0.0084-0.2995 0.3016-0.2958 0.7833 0.0084 1.0802l 4.1544 4.0548c 0.3042 0.2969 0.7902 0.2931 1.0897-0.0084 0.2995-0.3016 0.2958-0.7833-0.0084-1.0802z"},
			{tag:"path", d:"m 21.6435 1.2928 0.0469 5.7682c 0.0035 0.4268 0.3498 0.7678 0.7766 0.7647 0.4268-0.0032 0.7676-0.3493 0.7641-0.7761l-0.0469-5.7682c-0.0035-0.4268-0.3498-0.7678-0.7766-0.7647-0.4268 0.0032-0.7676 0.3493-0.7641 0.7761z"},
			{tag:"path", d:"m 26.1069 24.375c-0.4677 0.033-0.9728 0.1942-1.3332 0.3931-1.1368 0.6273-2.0556 2.9226-2.27 3.5024-0.2599-0.6887-1.1412-2.8637-2.2340-3.4666-0.7208-0.3978-1.9633-0.6605-2.4502 0-0.5916 0.8024 0.1647 2.1844 0.9008 2.8591 0.9822 0.9003 3.9275 0.8935 3.9275 0.8935 0 0 0.0005-0.034 0-0.036 0.5398-0.011 2.8424-0.097 3.7113-0.8935 0.7361-0.6746 1.4924-2.0566 0.9008-2.8591-0.2434-0.3302-0.6853-0.4259-1.1530-0.3931z"},
			{tag:"path", d:"m 22.4693 28.5688 0 10.6875"}
		]};
};

SymbolLibary.prototype.drawStop = function(node, calculte){
	return {
		x:10,
		y:10,
		width:30,
		height:30,
		items:[
			{tag:"path", fill:"#FFF", "stroke-width":"2", stroke:"#B00", d:"m "+node.getX()+","+node.getY()+" a 14,14 0 1 0 0.0636,-0.065 z m 0,0 20.73215,21.0846"}		
		]};
};
