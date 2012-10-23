package org.sdmlib.serialization.bytes;

import org.sdmlib.serialization.IdMapFilter;

public class ByteFilter extends IdMapFilter{
	private boolean isDynamic;
	private boolean isLenCheck;

	public ByteFilter(){
	}
	public ByteFilter(boolean isLenCheck, boolean isDynamic){
		this.isLenCheck=isLenCheck;
		this.isDynamic=isDynamic;
	}

	public boolean isLenCheck() {
		return isLenCheck;
	}

	public void setLenCheck(boolean isLenCheck) {
		this.isLenCheck = isLenCheck;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public void setDynamic(boolean isDynamic) {
		this.isDynamic = isDynamic;
	}
}
