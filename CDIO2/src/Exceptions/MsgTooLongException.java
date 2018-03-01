package Exceptions;

public class MsgTooLongException extends Exception{

	String msg;
	
	public MsgTooLongException(String msg) {
		this.msg = msg;
	}
}
