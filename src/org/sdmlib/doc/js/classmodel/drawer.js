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
HTMLDrawer = function() {};
HTMLDrawer.prototype.showInfoBox = function(){return true;}
HTMLDrawer.prototype.isShowRaster = function(){return true;}
HTMLDrawer.prototype.clearBoard = function(graph){};
HTMLDrawer.prototype.createContainer = function(){
	var board = document.createElement("div");
	board.className="Board";
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

		htmlElement.style.left=node.x+"px";
		htmlElement.style.top=node.y+"px";
		htmlElement.style.zIndex=5000;
		htmlElement.addEventListener("mousedown", startDrag, false);
		htmlElement.addEventListener("mouseover", showinfo, false);
		htmlElement.addEventListener("mouseout", fadeout, false);

		if(node.typ=="subgraph"){
			var options = new Options();
			options.rootElement = htmlElement;
			options.display = graph.options.display;
			options.bar = false;
			options.parent = graph;
			node.g = new Graph(node.graph, options);
			node.g.layout();
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
			htmlElement.innerHTML = node.content_plain;return htmlElement;
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
		//htmlElement.innerHTML = text + "</div>";
		htmlElement.innerHTML = text;
		htmlElement.node = node;
		node.htmlElement = htmlElement;
		return htmlElement;
};

HTMLDrawer.prototype.createInfo = function(x, y, text, calculate){
	var info = document.createElement("div");
	info.style.position = "absolute";
	info.style.top = y;
	info.style.left = x;
	info.innerHTML = text;
	return info;
};

HTMLDrawer.prototype.createLine = function(x1, y1, x2, y2, style){
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
SVGDrawer.prototype.showInfoBox = function(){return false;}
SVGDrawer.prototype.isShowRaster = function(){return false;}
SVGDrawer.prototype.clearBoard = function(graph){};
SVGDrawer.prototype.createContainer = function(){
	var board = document.createElementNS("http://www.w3.org/2000/svg", "svg");
	board.rasterElements=[];
	board.setAttribute('xmlns', "http://www.w3.org/2000/svg");
	return board;
};


SVGDrawer.prototype.getWidth = function(label, board, calculate){
	var text = document.createElementNS("http://www.w3.org/2000/svg", "text");
	text.appendChild(document.createTextNode(label));
	text.style.fontSize="12px";
	board.appendChild(text);
	var width =text.getBoundingClientRect().width;
	board.removeChild(text);
	return width;
}
SVGDrawer.prototype.getHTMLNode = function(node, graph){
	var ns = "http://www.w3.org/2000/svg";
	var group = document.createElementNS(ns, "g");
	group.setAttribute('transform', "translate("+node.x+" "+node.y+")");
	var width=0;
	var height=30;
	if(graph.typ=="object"){
		width = Math.max(width, this.getWidth(node.id.charAt(0).toLowerCase() + node.id.slice(1), graph.board));
	}else{
		width = Math.max(width, this.getWidth(node.id, graph.board));
	}
	if(node.attributes){
		height = height + node.attributes.length*20;
		for(var a in node.attributes){
			var attribute = node.attributes[a];
			width = Math.max(width, this.getWidth(attribute, graph.board));
		}
	} 
	height += 10;
	width += 20;
	var textwidth=width-10;

	if(node.content_src){
		var image = document.createElementNS(ns, "image");
		image.setAttribute('height', node.height);
		image.setAttribute('width', node.width);
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
		text.setAttribute("style", "font-size: 12px;");
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
		text.setAttribute("style", "text-decoration: underline;font-size: 12px;");
		text.appendChild(document.createTextNode(node.id.charAt(0).toLowerCase() + node.id.slice(1)));
	}else{
		text.setAttribute("style", "font-size: 12px;");
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
			text.setAttribute("style", "font-size: 12px;");
			text.appendChild(document.createTextNode(attribute));
			group.appendChild(text);
			y += 20;
		}
	}
	//group.innerHTML = text;
	return group;
};

SVGDrawer.prototype.createInfo = function(x, y, text, calculate){
	return null;
};

SVGDrawer.prototype.createLine = function(x1, y1, x2, y2, style){
	var line = document.createElementNS("http://www.w3.org/2000/svg", "line");
	line.setAttribute('x1', x1);
	line.setAttribute('y1', y1);
	line.setAttribute('x2', x2);
	line.setAttribute('y2', y2);
	if(style=="DOTTED"){
		line.setAttribute('style', "stroke:#000;stroke-miterlimit:4;stroke-dasharray:1, 1;");
	}else{
// 	if(style=="solid"){
		line.setAttribute('style', "stroke:#000;");
	}
	return line;
};


CanvasDrawer = function() {};
CanvasDrawer.prototype.clearBoard = function(graph){
	var canvas = graph.board;
	var context = canvas.getContext('2d');
	context.clearRect(0, 0, canvas.width, canvas.height);
};
CanvasDrawer.prototype.showInfoBox = function(){return false;}
CanvasDrawer.prototype.isShowRaster = function(){return true;}
CanvasDrawer.prototype.createContainer = function(){
	var board = document.createElement("canvas");
	board.rasterElements=[];
	return board;
};

CanvasDrawer.prototype.getWidth = function(text, canvas){
};
CanvasDrawer.prototype.getHTMLNode = function(node, graph, calculate){
	var canvas = graph.board;
	var context = canvas.getContext('2d');
	
	context.beginPath();
      context.rect(188, 50, 200, 100);
      context.fillStyle = 'yellow';
      context.fill();
      context.lineWidth = 7;
      context.strokeStyle = 'black';
      context.stroke();
	
	// Calculate Height
	/*
var group = document.createElementNS("http://www.w3.org/2000/svg", "g");
	group.setAttribute('transform', "translate("+node.x+" "+node.y+")");
	var width=0;
	var height=30;
	if(graph.typ=="object"){
		width = Math.max(width, this.getWidth(node.id.charAt(0).toLowerCase() + node.id.slice(1), graph.board));
	}else{
		width = Math.max(width, this.getWidth(node.id, graph.board));
	}
	if(node.attributes){
		height = height + node.attributes.length*20;
		for(var a in node.attributes){
			var attribute = node.attributes[a];
			width = Math.max(width, this.getWidth(attribute, graph.board));
		}
	}
	height += 10;
	width += 20;
	var textwidth=width-10;

	if(node.content_src){
		group.innerHTML = '<image height="'+node.height+'" width="'+node.width+'" xlink:href="'+node.content_src+'" xmlns:xlink="http://www.w3.org/1999/xlink"/>';return group;
	}
	if(node.content_svg){
		group.innerHTML = node.content_svg;return group;
	}
	if(node.content_plain){
		group.innerHTML = '<text text-anchor="left" x="10" style="font-size: 12px;">'+node.content_plain+'</text>';return group;
	}

	var text= '<rect height="'+height+'" width="'+width+'" style="fill:none;stroke:#000;stroke-width:1px;" />';
	
	if(graph.typ=="object"){
		text += '<text text-anchor="middle" width="'+textwidth+'" x="'+width/2+'" y="20" style="text-decoration: underline;font-size: 12px;">'+ node.id.charAt(0).toLowerCase() + node.id.slice(1) +"</text>";
	}else{
		text += '<text text-anchor="middle" width="'+textwidth+'" x="'+width/2+'" y="20" style="font-size: 12px;">'+ node.id+ "</text>";
	}
	text += '<line x1="0" y1="30" x2="'+width+'" y2="30" style="stroke:#000;"/>';
	if(node.attributes){
		var y = 50;
		for(var a in node.attributes){
			var attribute = node.attributes[a];
			text += '<text text-anchor="left" width="'+textwidth+'" x="10" y="'+y+'" style="font-size: 12px;">'+attribute+'</text>';
			y += 20;
		}
	}
	group.innerHTML = text;
	return group;
context.font = '30pt Calibri';
      context.textAlign = 'center';
      context.fillStyle = 'blue';
      context.fillText(text, x, y);

      // get text metrics
      var metrics = context.measureText(text);
      var width = metrics.width;*/
	  return context;
};
CanvasDrawer.prototype.createInfo = function(x, y, text, calculate){
	return null;
};
CanvasDrawer.prototype.createLine = function(x1, y1, x2, y2, style){
};
