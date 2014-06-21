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
Object_create = Object.create || function (o) {var F = function() {};F.prototype = o; return new F();};
Drawer = function(){};
Drawer.prototype.isShowRaster = function(){return false;}
Drawer.prototype.showInfoBox = function(){return false;}
Drawer.prototype.clearBoard = function(graph){};
Drawer.prototype.createText = function(text){return document.createTextNode(text);}
Drawer.prototype.createSubGraph = function(graph, node, element){
	var options = new Options();
	options.rootElement = element;
	options.display = graph.options.display;
	options.bar = false;
	options.parent = graph;
	node.g = new Graph(node.graph, options);
	node.g.layout();
};
Drawer.prototype.setSize = function(item, x, y){item.width = x;item.height = y;};

HTMLDrawer = function() {};
HTMLDrawer.prototype = Object_create(Drawer.prototype);
HTMLDrawer.prototype.showInfoBox = function(){return true;}
HTMLDrawer.prototype.isShowRaster = function(){return true;}
HTMLDrawer.prototype.setPos = function(item, x, y){item.style.left = x;item.style.top = y;};
HTMLDrawer.prototype.setSize = function(item, x, y){item.style.width = x;item.style.height = y;};
HTMLDrawer.prototype.createContainer = function(graph){
	var board = document.createElement("div");
	board.className="Board";
	board.graph = graph;
	board.rasterElements=[];
	return board;
};
HTMLDrawer.prototype.getHTMLNode = function(node, graph, calculate){
	var htmlElement = document.createElement("div");
	if(node.typ=="patternObject"){
		htmlElement.className="patternElement";
	}else if(graph.typ=="object"){
		htmlElement.className="objectElement";
	}else{
		htmlElement.className="classElement";
	}
	this.setPos(htmlElement, node.x, node.y);
	htmlElement.style.zIndex=5000;
	htmlElement.addEventListener("mousedown", startDrag, false);
	htmlElement.addEventListener("mouseover", showinfo, false);
	htmlElement.addEventListener("mouseout", fadeout, false);

	if(node.typ=="subgraph"){
		this.createSubGraph(graph, node, htmlElement);
		htmlElement.style.width = node.g.board.style.width;
		htmlElement.style.height = node.g.board.style.height;
		return htmlElement;
	}

	if(node.content_src){
		htmlElement.innerHTML = '<img src="'+node.content_src+'" />';return htmlElement;
	}
	if(node.content_html){
		htmlElement.innerHTML = node.content_html;return htmlElement;
	}
	if(node.content_plain){
		htmlElement.appendChild(this.createText(node.content_plain));return htmlElement;
	}
	var text = "<table border=0>";
	if(node.headimage){
		text += "<tr><td><img src=\""+node.headimage+"\" /></td></tr>";
	}
	if(node.headinfo){
		text += "<tr><td class=\"head\">"+node.headinfo+"</td></tr>";
	}

	if(graph.typ=="object"){
		info = "<u>" + node.id.charAt(0).toLowerCase() + node.id.slice(1) + "</u>";
	}else{
		info = node.id
	}
	if(node.href){
		info = "<a href=\""+node.href+"\">" + info + "</a>";
	}
	text += "<tr><th>" + info + "</th></tr>";
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
	htmlElement.innerHTML = text;
	htmlElement.node = node;
	node.htmlElement = htmlElement;
	return htmlElement;
};

HTMLDrawer.prototype.createInfo = function(x, y, text, calculate){
	var info = document.createElement("div");
	info.className="EdgeInfo";
	this.setPos(info, x, y);
	info.innerHTML = text;
	return info;
};

HTMLDrawer.prototype.createLine = function(x1, y1, x2, y2, graph, style){
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
	line.style.borderBottomStyle= style;

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

SVGDrawer = function() {};
SVGDrawer.prototype = Object_create(Drawer.prototype);
SVGDrawer.prototype.createContainer = function(graph){
	var board = document.createElementNS("http://www.w3.org/2000/svg", "svg");
	board.rasterElements=[];
	board.graph = graph;
	if(!isIE ()){
		board.setAttribute('xmlns', "http://www.w3.org/2000/svg");
	}
	return board;
};

SVGDrawer.prototype.getWidth = function(label, graph, calculate){
	var text = document.createElementNS("http://www.w3.org/2000/svg", "text");
	text.appendChild(document.createTextNode(label));
	text.style.fontSize=graph.options.fontsize*"px";
	graph.board.appendChild(text);
	var width =text.getBoundingClientRect().width;
	graph.board.removeChild(text);
	return width;
}
SVGDrawer.prototype.getHTMLNode = function(node, graph){
	var ns = "http://www.w3.org/2000/svg";
	var group = document.createElementNS(ns, "g");
	group.setAttribute('transform', "translate("+node.x+" "+node.y+")");
	var width=0;
	var height=30;
	if(graph.typ=="object"){
		width = Math.max(width, this.getWidth(node.id.charAt(0).toLowerCase() + node.id.slice(1), graph));
	}else{
		width = Math.max(width, this.getWidth(node.id, graph));
	}
	if(node.attributes){
		height = height + node.attributes.length*20;
		for(var a in node.attributes){
			var attribute = node.attributes[a];
			width = Math.max(width, this.getWidth(attribute, graph));
		}
	} 
	height += 10;
	width += 20;
	var textwidth=width-10;

	if(node.content_src){
		var image = document.createElementNS(ns, "image");
		image.setAttribute('height', node.height);
		image.setAttribute('width', node.width);
		image.setAttribute('xmlns:xlink', "http://www.w3.org/1999/xlink");
		image.setAttributeNS("http://www.w3.org/1999/xlink", 'href',node.content_src);
		group.appendChild(image);
		return group;
	}
	if(node.content_svg){
		group.innerHTML = node.content_svg;return group;
	}
	if(node.content_plain){
		var text = document.createElementNS(ns, "text");
		text.setAttribute("text-anchor", "left");
		text.setAttribute("x", "10");
		text.setAttribute("style", "font-size:"+graph.options.fontsize+"px;");
		var textNode = document.createTextNode(node.content_plain)
		text.appendChild(textNode);
		group.appendChild(text);
		return group;
	}
	var rect = document.createElementNS(ns, "rect");
	rect.setAttribute("height", height);
	rect.setAttribute("width", width);
	rect.setAttribute("style", "fill:none;stroke:#000;stroke-width:1px;");
	group.appendChild(rect);
	var text = document.createElementNS(ns, "text");
	text.setAttribute("text-anchor", "middle");
	text.setAttribute("width", textwidth);
	text.setAttribute("x", ""+width/2);
	text.setAttribute("y", "20");

	if(graph.typ=="object"){
		text.setAttribute("style", "text-decoration: underline;font-size:"+graph.options.fontsize+"px;");
		text.appendChild(document.createTextNode(node.id.charAt(0).toLowerCase() + node.id.slice(1)));
	}else{
		text.setAttribute("style", "font-size: "+graph.options.fontsize+"px;");
		text.appendChild(document.createTextNode(node.id));
	}
	group.appendChild(text);
	var line = document.createElementNS(ns, "line");
	line.setAttribute("x1", 0);
	line.setAttribute("y1", 30);
	line.setAttribute("x2", width);
	line.setAttribute("y2", 30);
	line.setAttribute("style", "stroke:#000;");
	group.appendChild(line);
	if(node.attributes){
		var y = 50;
		for(var a in node.attributes){
			var attribute = node.attributes[a];
			var text = document.createElementNS(ns, "text");
			text.setAttribute("text-anchor", "left");
			text.setAttribute("width", ""+textwidth);
			text.setAttribute("x", 10);
			text.setAttribute("y", y);
			text.setAttribute("style", "font-size:"+graph.options.fontsize+"px;");
			text.appendChild(document.createTextNode(attribute));
			group.appendChild(text);
			y += 20;
		}
	}
	return group;
};

SVGDrawer.prototype.createInfo = function(x, y, text, calculate){
	return null;
};

SVGDrawer.prototype.createLine = function(x1, y1, x2, y2, graph, style){
	var line = document.createElementNS("http://www.w3.org/2000/svg", "line");
	line.setAttribute('x1', x1);
	line.setAttribute('y1', y1);
	line.setAttribute('x2', x2);
	line.setAttribute('y2', y2);
	if(style=="DOTTED"){
		line.setAttribute('style', "stroke:#000;stroke-miterlimit:4;stroke-dasharray:1, 1;");
	}else{
		line.setAttribute('style', "stroke:#000;");
	}
	return line;
};

CanvasDrawer = function() {};
CanvasDrawer.prototype = Object_create(Drawer.prototype);
CanvasDrawer.prototype.clearBoard = function(graph){
	var canvas = graph.board;
	var context = canvas.getContext('2d');
	context.clearRect(0, 0, canvas.width, canvas.height);
};
CanvasDrawer.prototype.createContainer = function(graph){
	var board = document.createElement("canvas");
	board.rasterElements=[];
	board.graph = graph;
	return board;
};
CanvasDrawer.prototype.getWidth = function(text, graph){
	var context = graph.board.getContext('2d');
	context.font = graph.options.fontsize+"px Arial";
	var metrics = context.measureText(text);
	return metrics.width;

};
CanvasDrawer.prototype.getHTMLNode = function(node, graph, calculate){
	var canvas = graph.board;

	// Calculate Height
	var width=0;
	var height=20;
	if(graph.typ=="object"){
		width = Math.max(width, this.getWidth(node.id.charAt(0).toLowerCase() + node.id.slice(1), graph));
	}else{
		width = Math.max(width, this.getWidth(node.id, graph));
	}
	if(node.attributes){
		height = height + node.attributes.length*20;
		for(var a in node.attributes){
			var attribute = node.attributes[a];
			width = Math.max(width, this.getWidth(attribute, graph));
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
		img.src =  node.content_src;
		img.graph =  graph;
		img.node = node;
		this.loader.appendImg(img);
		return null;
	}
	if(node.content_plain){
		context.font = graph.options.fontsize+"px Arial";
		context.fillText(node.content_plain, node.x, node.y);
		return null;
	}

	context.beginPath();
	context.rect(node.x, node.y, node.width, node.height);
	context.lineWidth = 1;
	context.strokeStyle = 'black';
	context.stroke();

	this.createLine(node.x, node.y+20, node.x + node.width, node.y+20, graph);

	var context = canvas.getContext('2d');
	context.font = graph.options.fontsize+"px Arial";
	var text="";
	if(graph.typ=="object"){
		text = node.id.charAt(0).toLowerCase() + node.id.slice(1);
		var start = node.x + (node.width - this.getWidth(text, graph))/2;
		this.createLine(start, node.y+16, start + this.getWidth(text, graph), node.y+16, graph);
	}else{
		text = node.id;
	}
	context.fillText(text, node.x + (node.width - this.getWidth(text, graph))/2, node.y + 15);

	if(node.attributes){
		var y = node.y+40;
		for(var a in node.attributes){
			var attribute = node.attributes[a];
			var context = canvas.getContext('2d');
			context.font = graph.options.fontsize+"px Arial";
			context.fillText(attribute, node.x + 10, y);
			y += 20;
		}
	}
	return null;
};
CanvasDrawer.prototype.createInfo = function(x, y, text, calculate){
	return null;
};
CanvasDrawer.prototype.createLine = function(x1, y1, x2, y2, graph, style){
	var canvas = graph.board;
	var context = canvas.getContext('2d');
	if(style=="DOTTED"){
		context.setLineDash([1,2]);
	}
	context.moveTo(x1, y1);
	context.lineTo(x2, y2);
	context.stroke();
	return null;
};
ImageLoader = function() {this.images=[];};
ImageLoader.prototype.appendImg = function(img){
	img.loader = this;
	img.onload = this.onloadImage;
	this.images.push(img);
};
ImageLoader.prototype.onloadImage = function(event){
	var img = event.target;
	var canvas = img.graph.board;
};
Loader = function() {this.images=[];};
Loader.prototype.resetDrawer = function(){
	if(this.images.length==0){
		try{
			var img = document.createElement("img");
			img.src =  this.graph.board.toDataURL();
			this.graph.clearBoard();
			this.graph.board = img;
			this.graph.root.appendChild(img);
		} catch (e) {
			this.graph.clearBoard();
			this.graph.drawer = this.oldDrawer;
			this.graph.drawGraph(0,0);
			alert("Browser nicht unterstuetzt");
		}
	}else{
		var img = this.images.pop();
		this.graph.root.appendChild(img);
	}
};
Loader.prototype.appendImg = function(img){
	img.loader = this;
	img.crossOrigin = 'anonymous';
	img.onload = this.onloadImage;
	this.images.push(img);
}
Loader.prototype.onloadImage = function(event){
	var img = event.target;
	var canvas = img.graph.board;
	var context = canvas.getContext('2d');
	context.drawImage(img, img.node.x, img.node.y);
	img.graph.root.removeChild(img);
	img.loader.resetDrawer();
};
