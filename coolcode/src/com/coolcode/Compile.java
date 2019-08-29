package com.coolcode;
import java.io.*;  
public class Compile {
	private String fileinputaddress;
	private String fileoutputaddress;
	private String inputfolder;
	private String outputfolder;
	
	public Compile(){
        inputfolder="C:/Users/HP/Desktop/javajar/output/";
        outputfolder="C:/Users/HP/Desktop/javajar/output/";
        fileinputaddress=inputfolder+"inputtemp.c";
		fileoutputaddress=outputfolder+"outputtemp.txt";
	}
	public String getFileInputAddress(){
		return fileinputaddress;
	}
	public void setFileInputAddress(String s){
		fileinputaddress=s;
	}
	public String getFileOutputAddress(){
		return fileoutputaddress;
	}
	public void setFileOutputAddress(String s){
		fileoutputaddress=s;
	}
	
  public void compiledOutput(){
    try {
	  System.out.println(getFileInputAddress());
	  //String command="start cmd.exe /K \"g++ "+inputfolder+fileinputaddress+"\"";
      String command="start cmd.exe /K \"ls &> a.txt\"";
	  //Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"g++ "+inputfolder+fileinputaddress+" && exit\"");
	  ProcessBuilder pb = new ProcessBuilder("cmd", "/c", command);
	  Process shell = pb.start();
	  shell.waitFor();
	  System.out.println("shell");
	  
	  new Thread(new Runnable(){
	        public void run() {
	          BufferedReader br_in = null;
	          try {
	            br_in = new BufferedReader(new InputStreamReader(shell.getInputStream()));
	            String buff = null;
	            while ((buff = br_in.readLine()) != null) {
	              System.out.println("Process out :" + buff);
	              try {Thread.sleep(100); } catch(Exception e) {}
	            }
	            br_in.close();
	          }
	          catch (IOException ioe) {
	            System.out.println("Exception caught printing process output.");
	            ioe.printStackTrace();
	          }
	          finally {
	            try {
	              br_in.close();
	            } catch (Exception ex) {}
	          }
	        }
	      }).start();
	  System.out.println("hello");
	  
	      new Thread(new Runnable(){
	        public void run() {
	          BufferedReader br_err = null;
	          try {
	            br_err = new BufferedReader(new InputStreamReader(shell.getErrorStream()));
	            String buff = null;
	            while ((buff = br_err.readLine()) != null) {
	              System.out.println("Process err :" + buff);
	              try {Thread.sleep(100); } catch(Exception e) {}
	            }
	            br_err.close();
	          }
	          catch (IOException ioe) {
	            System.out.println("Exception caught printing process error.");
	            ioe.printStackTrace();
	          }
	          finally {
	            try {
	              br_err.close();
	            } catch (Exception ex) {}
	          }
	        }
	      }).start();
	  /*BufferedReader input = new BufferedReader(new InputStreamReader(shell.getInputStream()));
      while ((line = input.readLine()) != null){
    	  System.out.println(line);  
      }
      input.close();
      command="start cmd.exe /K \"a.exe &>> " + outputfolder+fileoutputaddress+"\"";
      //p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"a.exe &>> " + outputfolder+fileoutputaddress+" && exit\"");
      pb = new ProcessBuilder("cmd", "/c", command);
      shell = pb.start();
      shell.waitFor();

      input = new BufferedReader(new InputStreamReader(shell.getInputStream()));
      while ((line = input.readLine()) != null){
    	  System.out.println(line);  
      }
      input.close();
      */
    }  
    catch (Exception err) {  
      err.printStackTrace();  
    }
  }  
}