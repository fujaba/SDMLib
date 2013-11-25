package org.sdmlib.serialization.yuml;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class Cardinality {
	private String sourceID;
	private String sourceClazz;
	private String targetID;
	private String targetClazz;
	private String cardinality;
	public boolean showed;

	public Cardinality withSource(YUMLEntity element) {
		this.sourceID = element.getId();
		this.sourceClazz = element.getClassName();
		return this;
	}

	public Cardinality withTarget(YUMLEntity element) {
		this.targetID = element.getId();
		this.targetClazz = element.getClassName();
		return this;
	}

	public String getSourceID() {
		return sourceID;
	}

	public Cardinality withSourceID(String sourceID) {
		this.sourceID = sourceID;
		return this;
	}

	public String getSourceClazz() {
		return sourceClazz;
	}

	public Cardinality withSourceClazz(String sourceClazz) {
		this.sourceClazz = sourceClazz;
		return this;
	}

	public String getTargetID() {
		return targetID;
	}

	public Cardinality withTargetID(String targetID) {
		this.targetID = targetID;
		return this;
	}

	public String getTargetClazz() {
		return targetClazz;
	}

	public Cardinality setTargetClazz(String targetClazz) {
		this.targetClazz = targetClazz;
		return this;
	}

	public String getCardinality() {
		return cardinality;
	}

	public Cardinality withCardinality(String cardinality) {
		this.cardinality = cardinality;
		return this;
	}

	public Cardinality reset() {
		this.showed = false;
		return this;
	}

	public boolean isShowed() {
		return showed;
	}

	public Cardinality withShowed(boolean value) {
		this.showed = value;
		return this;
	}
}
