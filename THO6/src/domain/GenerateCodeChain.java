package domain;

public abstract class GenerateCodeChain {

	public String output;
	
	public abstract void setNextChain(GenerateCodeChain nextChain);
	
	public abstract void getCode(String brt);
}
