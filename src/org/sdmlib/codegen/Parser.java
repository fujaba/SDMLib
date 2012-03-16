/*
   Copyright (c) 2012 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
  */
   
package org.sdmlib.codegen;

import java.nio.channels.NotYetConnectedException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.sdmlib.utils.StrUtil;

public class Parser
{

   private static final char EOF = Character.MIN_VALUE;

   private static final String VOID = "void";

   private static final String CLASS = "class";

   public static final char COMMENT_START = 'c';

   public static final String PACKAGE = "package";

   public static final char LONG_COMMENT_END = 'd';

   public static final String ATTRIBUTE = "attribute";

   public static final String METHOD = "method";
   
   public static final String IMPORT = "import";

   public static final String CLASS_END = "classEnd";

   public static final String NAME_TOKEN = "nameToken";

   public static final String METHOD_END = "methodEnd";

   public static char NEW_LINE = '\n';
   
   private StringBuilder fileBody = null;

   private Token lookAheadToken = null;

   private Token currentToken;

   private char currentChar;

   private int index;
   
   private LinkedHashMap<String, SymTabEntry> symTab;
   
   public LinkedHashMap<String, SymTabEntry> getSymTab()
   {
      return symTab;
   }

   private int endPos;

   private char lookAheadChar;

   private int lookAheadIndex;

   private String searchString;

   private int methodBodyStartPos;  
   
   public int getMethodBodyStartPos()
   {
      return methodBodyStartPos;
   }
   
   class SearchStringFoundException extends RuntimeException { }
   
   public StringBuilder getFileBody()
   {
      return fileBody;
   }

   public Parser withFileBody(StringBuilder fileBody)
   {
      this.fileBody = fileBody;
      return this;
   }
   
   private Parser withInit(int startPos, int endPos) 
   {
      double result = 0;
      
      if (symTab == null)
      {
         symTab = new LinkedHashMap<String, SymTabEntry>();
      }
      else
      {
         symTab.clear();
      }
      
      
      currentChar = 0;
      
      index = startPos-1;
      lookAheadIndex = startPos-1;
      
      this.endPos = endPos;
      
      nextChar(); 
      nextChar();
      
      currentToken = new Token ();
      lookAheadToken = new Token ();
      previousToken = new Token ();
      
      nextToken();
      nextToken();
      
      currentRealToken = new Token();
      lookAheadRealToken = new Token();
      previousRealToken = new Token();
      
      nextRealToken();
      nextRealToken();
      
      return this;
   }

   public int indexOf(String searchString)
   {
      indexOfResult = -1;
      
      withInit(0, fileBody.length());
      
      this.searchString = searchString;
      
      try
      {
         parseFile();
      }
      catch (SearchStringFoundException e)
      {
         // found it, return indexOfResult
      }
      catch (Exception e) 
      {
         // problem with parsing. Return not found
         e.printStackTrace();
      }
      
      return indexOfResult;
   }

   private void parseFile()
   {
      // [packagestat] importlist classlist
      if (currentRealTokenEquals(PACKAGE))
      {
         parsePackageDecl();
      }
      
      while (currentRealTokenEquals(IMPORT))
      {
         parseImport();
      }
      
      parseClassDecl();
   }
 
   private void parseClassDecl()
   {
      // modifiers class name classbody
      parseModifiers();
      
      // skip keyword
      skip ("class");
      
      className = currentRealWord();
      
      // skip name
      nextRealToken();
      
      // TODO: extends 
      // TODO: implements 
      
      parseClassBody();     
   }

   private void parseClassBody()
   {
      // { classBodyDecl* }
      skip("{");
      while ( ! currentRealKindEquals(EOF) && ! currentRealKindEquals('}'))
      {
         parseMemberDecl();
      }
      
      if (currentRealKindEquals('}'))
      {
         checkSearchStringFound(CLASS_END, currentRealToken.startPos);
      }
      skip("}");      
   }

   private void parseMemberDecl()
   {
      // modifiers ( typeRef name [= expression ] | typeRef name '(' params ')' | classdecl ) ; 
      
      // TODO: annotations
      
      int startPos = currentRealToken.startPos;
      
      parseModifiers();
      
      if (currentRealTokenEquals(CLASS))
      {
         // parse nested class
         throw new NotYetConnectedException();
      }
      
      if (currentRealTokenEquals(className) && lookAheadRealToken.kind == '(')
      {
         // TODO: constructor
         skip(className);
         parseFormalParamList();
         parseBlock();
      }
      else
      {
         String type = parseTypeRef();

         String memberName = currentRealWord();
         nextRealToken();

         if (currentRealKindEquals('='))
         {
            // field declaration with initialisation
            skip("=");

            parseExpression();

            skip(";");

            symTab.put(memberName, 
               new SymTabEntry()
               .withMemberName(memberName)
               .withKind(ATTRIBUTE)
               .withType(type)
               );

            checkSearchStringFound(ATTRIBUTE+":"+memberName, startPos);
         }
         else if (currentRealKindEquals(';'))
         {
            // field declaration
            skip(";");
            
            symTab.put(memberName, 
               new SymTabEntry()
               .withMemberName(memberName)
               .withKind(ATTRIBUTE)
               .withType(type)
               );
            
            checkSearchStringFound(ATTRIBUTE+":"+memberName, startPos);
         }
         else if (currentRealKindEquals('('))
         {
            
            String params = parseFormalParamList();

            methodBodyStartPos = currentRealToken.startPos;
            
            parseBlock();
            
            String methodSignature = memberName + params;
            checkSearchStringFound(METHOD + ":" + methodSignature, startPos);
            
            symTab.put(methodSignature, 
               new SymTabEntry()
               .withMemberName(methodSignature)
               .withKind(METHOD)
               .withType(methodSignature)
               );
            }
      }
   }

   private void parseExpression()
   {
      // ... { ;;; } ;
      while ( ! currentRealKindEquals(EOF) && ! currentRealKindEquals(';'))
      {
         if (currentRealKindEquals('{'))
         {
            parseBlock();
         }
         else
         {
            nextRealToken();
         }
      }
   }

   private void parseBlock()
   {
      // { stat ... }
      skip("{");
      
      while ( ! currentRealKindEquals(EOF) && ! currentRealKindEquals('}'))
      {
         if (currentRealKindEquals('{'))
         {
            parseBlock();
         }
         else
         {
            nextRealToken();
         }
      }
      
      skip("}");      
   }

   private String parseTypeRef()
   {
      StringBuilder typeString = new StringBuilder();
      
      // (void | qualifiedName) <T1, T2> [] ...  
      String typeName = VOID;
      if (currentRealTokenEquals(VOID))
      {
         // skip void
         nextRealToken();
      }
      else
      {
         typeName = parseQualifiedName();
      }
      
      typeString.append(typeName);
      
      if (currentRealKindEquals('<'))
      {
         // <name, name, ...>
         skip("<"); typeString.append('<');
         
         // skip first name
         typeString.append(currentRealWord());
         nextRealToken();
         
         while (! currentRealKindEquals('>') && ! currentRealKindEquals(EOF))
         {
            typeString.append(currentRealWord());
            nextRealToken();
         }
         
         // should be a < now
         typeString.append(">");
         skip(">");
      }
      
      if (currentRealKindEquals('['))
      {
         typeString.append("[]");
         skip("[");
         skip("]");
      }
      
      if (currentRealKindEquals('.'))
      {
         typeString.append("...");
         skip(".");
         skip(".");
         skip(".");
      }
      
      // phew
      return typeString.toString();
   }

   private String parseFormalParamList()
   {
      StringBuilder paramList = new StringBuilder().append('(');
      
      // '(' (type name[,] )* ') [throws type , (type,)*] 
      skip("(");
      
      while ( ! currentRealKindEquals(EOF) && ! currentRealKindEquals(')'))
      {
         int typeStartPos = currentRealToken.startPos;
         
         parseTypeRef();
         
         int typeEndPos = currentRealToken.startPos-1;
         
         paramList.append(fileBody.substring(typeStartPos, typeEndPos));
         
         // skip param name
         nextRealToken();
         
         if (currentRealKindEquals(','))
         {
            skip(",");
            paramList.append(',');
         }
      }
      
      skip(")");
      
      paramList.append(')');
      
      return paramList.toString();
   }

   private boolean currentRealKindEquals(char c)
   {
      return currentRealToken.kind == c;
   }

   private String currentRealWord()
   {
      return currentRealToken.text.toString();
   }

   private boolean currentRealTokenEquals(String word)
   {
      return StrUtil.stringEquals(currentRealWord(), word);
   }

   private void parseModifiers()
   {
      // names != class
      String modifiers = " public protected private static abstract final native synchronized transient volatile strictfp ";
      while (modifiers.indexOf(" " + currentRealWord() + " ") >= 0)
      {
         nextRealToken();
      }
   }

   private void parseImport()
   {
      // import qualifiedName [. *];
      int startPos = currentRealToken.startPos;
      nextRealToken();
      parseQualifiedName();
      if (currentRealToken.kind == '*')
      {
         skip("*");
      }
      skip(";");
   }

   private void parsePackageDecl()
   {
      int startPos = currentRealToken.startPos;
      nextRealToken();
      parseQualifiedName();
      skip(";");
      checkSearchStringFound(PACKAGE, startPos);
   }

   private void checkSearchStringFound(String foundElem, int startPos)
   {
      if (StrUtil.stringEquals(searchString, foundElem))
      {
         indexOfResult = startPos;
         throw new SearchStringFoundException();
      }
   }

   private String parseQualifiedName()
   {
      // return dotted name
      int startPos = currentRealToken.startPos;
      int endPos = currentRealToken.endPos;
      nextRealToken();
      
      while (currentRealKindEquals('.') && ! (lookAheadRealToken.kind == '.') && ! currentRealKindEquals(EOF))
      {
         skip(".");
         
         // read next name
         nextRealToken();
         endPos = currentRealToken.endPos;
      }
      
      return fileBody.substring(startPos, endPos+1);
   }

   private void skip(char c)
   {
      if (currentRealKindEquals(c))
      {
         nextRealToken();
      }
      else
      {
         throw new RuntimeException("parse error");
      }
   }
   private void skip(String string)
   {
      if (currentRealTokenEquals(string))
      {
         nextRealToken();
      }
      else
      {
         System.err.println("Parser Error: expected token " + string + " found " + currentRealWord() + " at pos " + currentRealToken.startPos + " in file \n" 
            + fileName);
         throw new RuntimeException("parse error");
      }
   }

   public Token currentRealToken; 
   public Token lookAheadRealToken;
   public Token previousRealToken;

   public int indexOfResult;

   private Token previousToken;

   private String className;

   public int lastIfStart;
   public int lastIfEnd;

   
   
   private void nextRealToken()
   {
      Token tmp = previousRealToken;
      previousRealToken = currentRealToken;
      currentRealToken = lookAheadRealToken;
      lookAheadRealToken = tmp;
      lookAheadRealToken.kind = EOF;
      lookAheadRealToken.text.delete(0, lookAheadRealToken.text.length());
      
      
      // parse comments and skip new lines
      while (currentToken.kind == COMMENT_START || currentToken.kind == NEW_LINE)
      {
         if (currentToken.text.indexOf("/*") == 0 )
         {
            parseLongComment();
         }
         else if (currentToken.text.indexOf("//") == 0)
         {
            parseLineComment();
         }
         else
         {
            nextToken();
         }
      }
      
      // parse string constants as one real token
      if (currentToken.kind == '"')
      {
         int constStartPos = currentToken.startPos;

         parseStringConstant();
         
         lookAheadRealToken.kind = '"';
         lookAheadRealToken.text.append(fileBody.substring(constStartPos, previousToken.startPos+1));
         lookAheadRealToken.startPos = constStartPos;
         lookAheadRealToken.endPos = previousToken.startPos;
      }
      else
      {
         lookAheadRealToken.kind = currentToken.kind;
         lookAheadRealToken.text.append(currentToken.text.toString());
         lookAheadRealToken.startPos = currentToken.startPos;
         lookAheadRealToken.endPos = currentToken.endPos;

         nextToken();
      }
   }
   
   private void parseLineComment()
   {
      // '//' ... \n
      
      // skip //
      nextToken();
      
      while (currentToken.kind != EOF && currentToken.kind != '\n')
      {
         nextToken();
      }
      
      // skip \n 
      nextToken();
   }

   private void parseStringConstant()
   {
      // " ... \" ... "
      skipBasicToken('"');
      
      // read until next "
      while (currentToken.kind != EOF && currentToken.kind != '"')
      {
         if (currentToken.kind == '\\')
         {
            // escape next char
            nextToken();
         }
         nextToken();
      }

      skipBasicToken('"');
   }

   private void skipBasicToken(char s)
   {
      if (currentToken.kind == s)
      {
         nextToken();
      }
   }

   private void parseLongComment()
   {
      // parse /* ... */ (nested?) 
      
      // skip /*
      nextToken();
      while (currentToken.kind != EOF && currentToken.kind != LONG_COMMENT_END)
      {
         nextToken();
      }
      
      // skip */
      nextToken();
   }

   private void nextToken()
   {
      Token tmp = previousToken;
      previousToken = currentToken;
      currentToken = lookAheadToken;
      
      lookAheadToken = tmp;
      lookAheadToken.kind = EOF;
      lookAheadToken.text.delete(0, lookAheadToken.text.length());
      
      char state = 'i';
      
      while (true)
      {
         switch (state) 
         {
         case 'i':
            if (Character.isLetter(currentChar) || (currentChar == '_'))
            {
               state = 'v';
               lookAheadToken.kind = 'v';
               lookAheadToken.text.append(currentChar);
               lookAheadToken.startPos = index;
            }
            else if (currentChar == EOF)
            {
               lookAheadToken.kind = EOF;
               lookAheadToken.startPos = index;
               lookAheadToken.endPos = index;
               return;
            }
            else if (Character.isDigit(currentChar))
            {
               state = '9';
               lookAheadToken.kind = '9';
               lookAheadToken.value = currentChar - '0';
               lookAheadToken.startPos = index;
            }
            else if (currentChar == '/' && (lookAheadChar == '*' || lookAheadChar == '/'))
            {
               // start of comment
               lookAheadToken.kind = COMMENT_START;
               lookAheadToken.startPos = index;
               lookAheadToken.text.append(currentChar);
               nextChar();
               lookAheadToken.text.append(currentChar);
               nextChar();
               return;
            }
            else if (currentChar == '*' && lookAheadChar == '/')
            {
               // start of comment
               lookAheadToken.kind = LONG_COMMENT_END;
               lookAheadToken.startPos = index;
               lookAheadToken.text.append(currentChar);
               nextChar();
               lookAheadToken.text.append(currentChar);
               nextChar();
               return;
            }
            else if ("+-*/\\\"'~=()><{}!.,@[]&|?;:#".indexOf(currentChar) >= 0)
            {
               lookAheadToken.kind = currentChar;
               lookAheadToken.text.append(currentChar);
               lookAheadToken.startPos = index;
               lookAheadToken.endPos = index;
               nextChar();
               return;
            }
            else if (currentChar == NEW_LINE)
            {
               lookAheadToken.kind = NEW_LINE;
               lookAheadToken.startPos = index;
               lookAheadToken.endPos = index;
               nextChar();
               return;
            }
            else if (Character.isWhitespace(currentChar))
            {
            }
            
            break;

         case '9':
            if (Character.isDigit(currentChar))
            {
               lookAheadToken.value = lookAheadToken.value * 10 + (currentChar - '0');
            }
            else if (currentChar == '.')
            {
               state = '8';
            }
            else
            {
               lookAheadToken.endPos = index-1;
               return;
            }
            break;
         
         case '8':
            if ( ! Character.isDigit(currentChar))
           {
               lookAheadToken.endPos = index-1;
               return;
            }
            break;
         
         case 'v':
            if (Character.isLetter(currentChar) 
                  || Character.isDigit(currentChar)
                  || currentChar == '_')
            {
               // keep reading
               lookAheadToken.text.append(currentChar);
            }
            else
            {
               lookAheadToken.endPos = index-1;
               return; // <==== sudden death
            }
            break;
            
         default:
            break;
         }
         
         
         nextChar();
      }
   }

   private void nextChar() 
   {
      currentChar = lookAheadChar;
      index = lookAheadIndex;
      lookAheadChar = 0;
      
      while ( lookAheadChar == 0 && lookAheadIndex < endPos-1) 
      {
         lookAheadIndex++;    
         
         lookAheadChar = fileBody.charAt(lookAheadIndex);         
      }
   }

   public int methodBodyIndexOf(String searchString, int searchStartPos)
   {
      indexOfResult = -1;
      lastIfStart = -1;
      lastIfEnd = -1;
      
      // initialize parser to start reading at pos
      withInit(searchStartPos, fileBody.length());
      
      this.searchString = searchString;
      
      try
      {
         parseBlockDetails();
      }
      catch (SearchStringFoundException e)
      {
         // found it, return indexOfResult
      }
      catch (Exception e) 
      {
         // problem with parsing. Return not found
         e.printStackTrace();
      }

      return indexOfResult;
   }

   private void parseBlockDetails()
   {
      // parse method and generate statement index
      skip('{');

      while ( ! currentRealKindEquals(EOF) && ! currentRealKindEquals('}'))
      {
         if (currentRealTokenEquals("if"))
         {
            lastIfStart = currentRealToken.startPos;
            skip("if");
            
            parseBracketExpressionDetails();
            
            parseBlockDetails();
            
            lastIfEnd = previousRealToken.startPos;
         }
         else if (currentRealKindEquals('v'))
         {
            checkSearchStringFound(NAME_TOKEN + ":" + currentRealWord(), currentRealToken.startPos);
            
            nextRealToken();
         }
         else if (currentRealKindEquals('{'))
         {
            parseBlockDetails();
         }
         else
         {
            nextRealToken();
         }
      }

      // checkSearchStringFound(METHOD_END, currentRealToken.startPos);
      skip('}');
   }

   private void parseBracketExpressionDetails()
   {
      skip('(');
      
      while ( ! currentRealKindEquals(EOF) 
         && ! currentRealKindEquals(')'))
      {
         if (currentRealKindEquals('('))
         {
            parseBracketExpressionDetails();
         }
         else if (currentRealKindEquals('v'))
         {
            checkSearchStringFound(NAME_TOKEN + ":" + currentRealWord(), currentRealToken.startPos);
            
            nextRealToken();
         }
         else
         {            
            nextRealToken();
         }
      }
      
      skip(')');      
   }

   private String fileName;
   
   public String getFileName()
   {
      return fileName;
   }
   
   public void setFileName(String fileName)
   {
      this.fileName = fileName;
   }
   
   public Parser withFileName(String fileName)
   {
      setFileName(fileName);
      return this;
   }
}

