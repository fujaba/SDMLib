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
HTMLDrawer.prototype.createContainer = function(canvasid){
	var board = document.createElement("div");
	board.className="Board";
	board.rasterElements=[];
	return board;
};
HTMLDrawer.prototype.getHTMLNode = function(node, i, graphtyp, board){
		var htmlElement = document.createElement("div");
		htmlElement.className="classElement";
		htmlElement.style.left=node.x+"px";
		htmlElement.style.top=node.y+"px";
		htmlElement.style.zIndex=5000;
		htmlElement.addEventListener("mousedown", startDrag, false);
		htmlElement.addEventListener("mouseover", showinfo, false);
		htmlElement.addEventListener("mouseout", fadeout, false);
		var head="";
		if(node.headimage){
			head = "<tr><td><img src=\""+node.headimage+"\" /></td></tr>";
		}
		var text = "<table border=0>"+head;
		if(graphtyp=="object"){
			text = text + "<tr><th><u>" + node.id.charAt(0).toLowerCase() + node.id.slice(1) + "</u></th></tr>";
		}else{
			text = text + "<tr><th>"+ node.id+"</th></tr>";
		}
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

HTMLDrawer.prototype.createInfo = function(x, y, text){
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
SVGDrawer.prototype.isShowRaster = function(){return true;}
SVGDrawer.prototype.createContainer = function(canvasid){
	var board = document.createElementNS("http://www.w3.org/2000/svg", "svg");
	board.rasterElements=[];
	board.setAttribute('xmlns', "http://www.w3.org/2000/svg");
	return board;
};


SVGDrawer.prototype.getWidth = function(label, board){
	var text = document.createElementNS("http://www.w3.org/2000/svg", "text");
	text.innerHTML = label;
	text.style.fontSize="12px";
	board.appendChild(text);
	var width =text.getBoundingClientRect().width;
	board.removeChild(text);
	return width;
}
SVGDrawer.prototype.getHTMLNode = function(node, i, graphtyp, board){
	var group = document.createElementNS("http://www.w3.org/2000/svg", "g");
	group.setAttribute('transform', "translate("+node.x+" "+node.y+")");
	var width=0;
	var height=30;
	if(graphtyp=="object"){
		width = Math.max(width, this.getWidth(node.id.charAt(0).toLowerCase() + node.id.slice(1), board));
	}else{
		width = Math.max(width, this.getWidth(node.id, board));
	}
	if(node.attributes){
		height = height + node.attributes.length*20;
		for(var a in node.attributes){
			var attribute = node.attributes[a];
			width = Math.max(width, this.getWidth(attribute, board));
		}
	}
	height += 10;
	width += 20;
	var textwidth=width-10;
	
	var text= '<rect height="'+height+'" width="'+width+'" style="fill:none;stroke:#000;stroke-width:1px;" />';
	
	if(graphtyp=="object"){
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
};

SVGDrawer.prototype.createInfo = function(x, y, text){
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