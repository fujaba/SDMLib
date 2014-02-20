package org.sdmlib.serialization.logic;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or - as soon they
 will be approved by the European Commission - subsequent
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
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class IfCondition implements Condition, SendableEntityCreator {
	public static final String EXPRESSION="expression";
	public static final String TRUECONDITION="truecondition";
	public static final String FALSECONDITION="falsecondition";

	private Condition expression;
	private Condition trueCondition;
	private Condition falseCondition;

	public IfCondition withExpression(Condition expression){
		this.expression = expression;
		return this;
	}
	
	public Condition getExpression() {
		return expression;
	}
	
	public IfCondition withTrue(Condition condition){
		this.trueCondition = condition;
		return this;
	}
	
	public Condition getTrue() {
		return trueCondition;
	}
	
	public IfCondition withFalse(Condition condition){
		this.falseCondition = condition;
		return this;
	}
	
	public Condition getFalse() {
		return falseCondition;
	}
	
	@Override
	public boolean matches(ValuesSimple values) {
		if(expression.matches(values)){
			if(trueCondition!=null){
				return trueCondition.matches(values);
			}
		}else{
			if(falseCondition!=null){
				return falseCondition.matches(values);
			}
		}
		return false;
	}
	
	@Override
	public String[] getProperties() {
		return new String[]{EXPRESSION, TRUECONDITION, FALSECONDITION};
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new IfCondition();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		if(EXPRESSION.equalsIgnoreCase(attribute)){
			return ((IfCondition)entity).getExpression();
		}
		if(TRUECONDITION.equalsIgnoreCase(attribute)){
			return ((IfCondition)entity).getTrue();
		}
		if(FALSECONDITION.equalsIgnoreCase(attribute)){
			return ((IfCondition)entity).getFalse();
		}
		return null;
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		if(EXPRESSION.equalsIgnoreCase(attribute)){
			((IfCondition)entity).withExpression((Condition) value);
			return true;
		}
		if(TRUECONDITION.equalsIgnoreCase(attribute)){
			((IfCondition)entity).withTrue((Condition) value);
			return true;
		}
		if(FALSECONDITION.equalsIgnoreCase(attribute)){
			((IfCondition)entity).withFalse((Condition) value);
			return true;
		}
		return false;
	}
}
