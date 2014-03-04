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
Lines = function() {
	this.source = new Item(new Pos(0,0),new Pos(0,0));
	this.target = new Item(new Pos(0,0),new Pos(0,0));
	this.sourceinfo = new Item(new Pos(0,0),new Pos(0,0));
	this.targetinfo = new Item(new Pos(0,0),new Pos(0,0));
	this.info = new Item(new Pos(0,0),new Pos(0,0));
};
Lines.prototype.createInfo = function(x, y, text){
	var info = document.createElement("div");
	info.style.position = "absolute";
	info.style.top = y;
	info.style.left = x;
	info.innerHTML = text;
	return info;
};

Lines.prototype.createLine = function(x1, y1, x2, y2, options){
	options = options || {};
	if (x2 < x1){
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
	if(IE){
		line.style.top = (y2 > y1) ? y1 + "px" : y2 + "px";
		line.style.left = x1 + "px";
		var nCos = (x2-x1)/length;
		var nSin = (y2-y1)/length;
		line.style.filter = "progid:DXImageTransform.Microsoft.Matrix(sizingMethod='auto expand', M11=" + nCos + ", M12=" + -1*nSin + ", M21=" + nSin + ", M22=" + nCos + ")";
	}else{
		var angle = Math.atan((y2-y1)/(x2-x1));
		line.style.top = y1 + 0.5*length*Math.sin(angle) + "px";
		line.style.left = x1 - 0.5*length*(1 - Math.cos(angle)) + "px";
		line.style.MozTransform = line.style.WebkitTransform = line.style.OTransform= "rotate(" + angle + "rad)";
	}
	return line;
};
Lines.prototype.addElement= function(board, list, element){
	list.push(element);
	board.appendChild(element);
};
Lines.PositionCombi ={
		LEFT_UP : "LEFT_UP",
		LEFT_RIGHT : "LEFT_RIGHT",
		LEFT_DOWN : "LEFT_DOWN",
		DOWN_LEFT : "DOWN_LEFT",
		DOWN_RIGHT : "DOWN_RIGHT",
		DOWN_UP : "DOWN_UP",
		RIGHT_LEFT : "RIGHT_LEFT",
		UP_DOWN : "UP_DOWN",
		UP_LEFT : "UP_LEFT",
		RIGHT_DOWN : "RIGHT_DOWN",
		UP_RIGHT : "UP_RIGHT",
		RIGHT_UP : "RIGHT_UP",
		DEFAULT : "DEFAULT"
};
Lines.Layout ={
		DIAG : 1,
		RECT : 0
};

Lines.prototype.draw = function(board, edge){
	this.source = new Item(new Pos(edge.source.x, edge.source.y), new Pos(edge.source.width, edge.source.height));
	this.target = new Item(new Pos(edge.target.x, edge.target.y), new Pos(edge.target.width, edge.target.height));
	this.source.center = new Pos(this.source.pos.x + (this.source.size.x / 2), this.source.pos.y + (this.source.size.y / 2));
	this.target.center = new Pos(this.target.pos.x + (this.target.size.x / 2), this.target.pos.y + (this.target.size.y / 2));
	this.start = new Pos(edge.source.x, edge.source.y);
	this.end = new Pos(edge.target.x, edge.target.y);
	this.sourceinfo = new Item(new Pos(0,0),new Pos(0,0));
	this.targetinfo = new Item(new Pos(0,0),new Pos(0,0));
	this.info = new Item(new Pos(0,0),new Pos(0,0));
};

Item = function(pos, size) {
	this.pos = pos;
	this.size = size;
	this.center=new Pos(0,0);
}

RectLine = function() {
	this.lineLayout=Lines.Layout.DIAG;
	this.size = 16;
	this.angle = 50;
	this.lineangle = 0;
	this.h = 0;
	this.angle1 = 0;
	this.angle2 = 0;
	this.top = new Pos(0,0);
	this.bot = new Pos(0,0);
};
RectLine.prototype = Object_create(Lines.prototype);
RectLine.prototype.$super = Lines.prototype;
RectLine.prototype.constructor = RectLine;
RectLine.prototype.draw = function(board, edge) {
	this.$super.draw(board,edge);
	
	var model = new LineModelCenter();
	model.draw(this);

	
	this.startArrow = new Pos(edge.source.x, edge.source.y);
	this.endArrow = new Pos(this.end.x, this.end.y);

	if(this.lineLayout==Lines.Layout.RECT){
		this.startArrow = new Pos(this.start.x, this.end.y);
	}
	// calculate the angle of the line
	this.lineangle=Math.atan2(this.end.y-this.startArrow.y,this.end.x-this.startArrow.x);
	// h is the line length of a side of the arrow head
	this.h=Math.abs(this.size/Math.cos(this.angle));
	this.angle1=this.lineangle+Math.PI+this.angle;
	this.top = new Pos(this.end.x+Math.cos(this.angle1)*this.h, this.end.y+Math.sin(this.angle1)*this.h);
	this.angle2=this.lineangle+Math.PI-this.angle;
	this.bot = new Pos(this.end.x+Math.cos(this.angle2)*this.h, this.end.y+Math.sin(this.angle2)*this.h);
	if(edge instanceof Generalisation){
		this.end = new Pos((this.top.x + this.bot.x) / 2, (this.top.y + this.bot.y) / 2);
	}
	edge.htmlElement = new Array();

	if(this.lineLayout==Lines.Layout.DIAG){
		this.addElement(board, edge.htmlElement, this.createLine(this.start.x, this.start.y, this.end.x, this.end.y));
	}
	if(this.lineLayout==Lines.Layout.RECT){
		this.addElement(board, edge.htmlElement, this.createLine(this.start.x, this.start.y, this.start.x, this.end.y));
		this.addElement(board, edge.htmlElement, this.createLine(this.start.x, this.end.y, this.end.x, this.end.y));
	}
	if(edge instanceof Generalisation){
		this.draw_arrow(board, edge.htmlElement);
	}
	
	if(edge.sourceinfo){
		this.addElement(board, edge.htmlElement, this.createInfo(this.sourceinfo.pos.x, this.sourceinfo.pos.y, edge.sourceproperty));
	}
	if(edge.info){
		this.addElement(board, edge.htmlElement, this.createInfo(this.info.pos.x, this.info.pos.y, edge.info));
	}
	if(edge.targetinfo){
		this.addElement(board, edge.htmlElement, this.createInfo(this.targetinfo.pos.x, this.targetinfo.pos.y, edge.targetproperty));
	}
};
RectLine.prototype.draw_arrow = function(board, elements) {
	this.addElement(board, elements, this.createLine(this.top.x, this.top.y, this.endArrow.x, this.endArrow.y));
	this.addElement(board, elements, this.createLine(this.bot.x, this.bot.y, this.endArrow.x, this.endArrow.y));
	this.addElement(board, elements, this.createLine(this.top.x, this.top.y, this.bot.x, this.bot.y));
};
LineModelCenter = function() {};
LineModelCenter.Position={UP:"UP",
		LEFT:"LEFT",
		RIGHT:"RIGHT",
		DOWN:"DOWN"};
LineModelCenter.prototype.getPosition= function(m , n, entity, refCenter){
	var x,y;
	var pos=new Array();
	var distance=new Array();
	x = entity.pos.x+entity.size.x;
	y = m*x+n;
	if(y>=entity.pos.y && y<=(entity.pos.y+entity.size.y)){
		pos.push(new Pos(x , y, LineModelCenter.Position.RIGHT));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	y = entity.pos.y;
	x = (y-n)/m;
	if(x>=entity.pos.x && x<=(entity.pos.x+entity.size.x)){
		pos.push(new Pos(x , y, LineModelCenter.Position.UP));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	x = entity.pos.x;
	y = m*x+n;
	if(y>=entity.pos.y && y<=(entity.pos.y+entity.size.y)){
		pos.push(new Pos(x , y, LineModelCenter.Position.LEFT));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	y = entity.pos.y+entity.size.y;
	x = (y-n)/m;
	if(x>=entity.pos.x && x<=(entity.pos.x+entity.size.x)){
		pos.push(new Pos(x , y, LineModelCenter.Position.DOWN));
		distance.push(Math.sqrt((refCenter.x-x)*(refCenter.x-x)+(refCenter.y-y)*(refCenter.y-y)));
	}
	if(pos.length<1){
		console.log("Line: "+x+":"+y);
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
LineModelCenter.prototype.draw = function(line){
	var divisor = (line.target.center.x - line.source.center.x);
	var m,n;
	var sourcePos,targetPos;
	if(divisor==0){
		// Must be UP_DOWN or DOWN_UP
		if(line.source.center.y<line.target.center.y){
			// UP_DOWN
			sourcePos = new Pos(line.source.center.x ,  (line.source.pos.y+line.source.size.y), LineModelCenter.Position.DOWN);
			targetPos = new Pos(line.target.center.x ,  (line.target.pos.y), LineModelCenter.Position.UP);
		}else{
			sourcePos = new Pos(line.source.center.x ,  (line.source.pos.y), LineModelCenter.Position.UP);
			targetPos = new Pos(line.target.center.x ,  (line.target.pos.y+line.target.size.y), LineModelCenter.Position.DOWN);
		}
	}else{
		m = (line.target.center.y - line.source.center.y) / divisor;
		n = line.source.center.y - (line.source.center.x * m);
		sourcePos = this.getPosition(m,n, line.source, line.target.center);
		targetPos = this.getPosition(m,n, line.target, sourcePos);
	}
	
	var pos = "";
	
	if(sourcePos&&targetPos){
		pos = sourcePos.id+ "_" + targetPos.id;
		//console.log("Line: "+sourcePos.x+":"+sourcePos.y+" - "+targetPos.x+":"+targetPos.y);
		line.start = new Pos(sourcePos.x, sourcePos.y);
		line.end = new Pos(targetPos.x, targetPos.y);
	}

	
	switch (pos) {
		case "RIGHT_LEFT":
			line.sourceinfo.pos = new Pos(line.start.x + 5, line.start.y - 22);
			line.targetinfo.pos = new Pos(line.end.x - line.target.size.x - 5, line.end.y - 22);
			line.info.pos = new Pos(((line.start.x + line.end.x) / 2) - (line.info.size.x/2), line.start.y - 22);
//			deletelabel.setPos(new Position((end.getX() + start.getX()) / 2, start.getY()));
			break;
		case "DOWN_UP":
			line.sourceinfo.pos = new Pos(line.start.x + 2, line.start.y + 5);
			line.targetinfo.pos = new Pos(line.end.x + 2, line.end.y - line.targetinfo.size.y - 5);
			line.info.pos = new Pos(line.start.x + 5, ((line.start.y + line.end.y) / 2) - (line.info.size.y/2));
//			deletelabel.setPos(new Position(end.getX(),(end.getY() + start.getY()) / 2));
			break;
		case "LEFT_RIGHT":
			line.sourceinfo.pos = new Pos(line.start.x - line.sourceinfo.size.x - 5, line.start.y - 22);
			line.targetinfo.pos = new Pos(line.end.x + 5, line.end.y - 22);
			line.info.pos = new Pos(((line.start.x + line.end.x) / 2) - (line.info.size.x/2), line.start.y - 22);
//			deletelabel.setPos(new Position((end.getX() + start.getX()) / 2, start.getY()));
			break;
		case "UP_DOWN":
			line.sourceinfo.pos = new Pos(line.start.x + 2, line.start.y - line.sourceinfo.size.y - 5);
			line.targetinfo.pos = new Pos(line.end.x + 2, line.end.y + 5);
			line.info.pos = new Pos(line.start.x + 5, ((line.start.y + line.end.y) / 2) - (line.info.size.y/2));
//			deletelabel.setPos(new Position(end.getX(),(end.getY() + start.getY()) / 2));			
			break;
		case "UP_LEFT":
			line.sourceinfo.pos = new Pos(line.start.x + 2, line.start.y - line.sourceinfo.size.y - 5);
			line.targetinfo.pos = new Pos(line.end.x - line.targetinfo.size.x - 5, line.end.y - 22);
			line.info.pos = new Pos(line.start.x, line.end.y - line.info.size.y - 5);
//			deletelabel.setPos(new Position(start.getX(), end.getY()));
			break;
		case "RIGHT_DOWN":
			line.sourceinfo.pos = new Pos(line.start.x + 5, line.start.y - 22);
			line.targetinfo.pos = new Pos(line.end.x + 2, line.end.y + 5);
			line.info.pos = new Pos(line.end.x - line.info.size.x - 5, line.start.y + 5);
//			deletelabel.setPos(new Position(end.getX(), start.getY()));
			break;
		case "UP_RIGHT":
			line.sourceinfo.pos = new Pos(line.start.x + 2, line.start.y - line.sourceinfo.size.y - 5);
			line.targetinfo.pos = new Pos(line.end.x + 5, line.end.y - 22);
			line.info.pos = new Pos(line.start.x - line.info.size.x - 5, line.end.y - line.info.size.y - 5);
//			deletelabel.setPos(new Position(start.getX(), end.getY()));
			break;
		case "LEFT_DOWN":
			line.sourceinfo.pos = new Pos(line.start.x - line.sourceinfo.size.x - 5, line.start.y - 22);
			line.targetinfo.pos = new Pos(line.end.x + 2, line.end.y + 5);
			line.info.pos = new Pos(line.end.x + 5, line.start.y + 5);
//			deletelabel.setPos(new Position(end.getX(), start.getY()));
			break;
		case "DOWN_LEFT":
			line.sourceinfo.pos = new Pos(line.start.x + 2, line.start.y + 5);
			line.targetinfo.pos = new Pos(line.end.x - line.targetinfo.size.x - 5, line.end.y - 22);
			line.info.pos = new Pos(line.start.x, line.end.y + 5);
//			deletelabel.setPos(new Position(start.getX(), end.getY()));
			break;
		case "RIGHT_UP":
			line.sourceinfo.pos = new Pos(line.start.x + 5, line.start.y - 22);
			line.targetinfo.pos = new Pos(line.end.x + 2, line.end.y - line.targetinfo.size.y - 5);
			line.info.pos = new Pos(line.end.x - line.info.size.x - 5, line.start.y - line.info.size.y - 5);
//			deletelabel.setPos(new Position(end.getX(), start.getY()));
			break;
		case "DOWN_RIGHT":
			line.sourceinfo.pos = new Pos(line.start.x + 2, line.start.y + 5);
			line.targetinfo.pos = new Pos(line.end.x + 5, line.end.y - 22);
			line.info.pos = new Pos(line.start.x - line.info.size.x - 5, line.end.y + 5);
//			deletelabel.setPos(new Position(start.getX(), end.getY()));
			break;
		case "LEFT_UP":
			line.sourceinfo.pos = new Pos(line.start.x - line.sourceinfo.size.x - 5, line.start.y - 22);
			line.targetinfo.pos = new Pos(line.end.x + 2, line.end.y - line.targetinfo.y - 5);
			line.info.pos = new Pos(line.end.x + 5, line.start.y - line.info.size.y - 5);
//			deletelabel.setPos(new Position(end.getX(), start.getY()));
			break;
		case Lines.PositionCombi.DEFAULT:
			//TODO something
			break;
		default:
			break;
	}
};