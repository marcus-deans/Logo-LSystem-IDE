//package oolala.view;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.net.URL;
//import javax.script.Invocable;
//
//public class LessLoader {
//  // ...
//  public URL loadLess(URL lessFile) {
//    try {
//      StringBuilder lessContent = new StringBuilder();
//      readFileContent(lessFile, lessContent);
//
//      Object rv = ((Invocable)engine).invokeFunction("parseString", lessContent.toString());
//      File f = File.createTempFile("less_", ".css");
//      f.deleteOnExit();
//
//      try(FileOutputStream out = new FileOutputStream(f) ) {
//        out.write(rv.toString().getBytes());
//        out.close();
//      }
//      return f.toURI().toURL();
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//// ...