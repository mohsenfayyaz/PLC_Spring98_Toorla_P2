import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import toorla.ast.Program;
import toorla.visitor.NameAnalyzerPreProcess;
import toorla.visitor.TreePrinter;
import toorla.visitor.Visitor;
import toorla.visitor.NameAnalyzer;

public class ToorlaCompiler {
    public void compile(CharStream textStream) {
        ToorlaLexer toorlaLexer = new ToorlaLexer( textStream );
        CommonTokenStream tokenStream = new CommonTokenStream( toorlaLexer );
        ToorlaParser toorlaParser = new ToorlaParser( tokenStream );
        Program toorlaASTCode = toorlaParser.program().mProgram;

        Visitor<Void> nameAnalyzerPreProcess = new NameAnalyzerPreProcess();
        toorlaASTCode.accept( nameAnalyzerPreProcess );



        Visitor<Void> nameAnalyzer = new NameAnalyzer();
        toorlaASTCode.accept( nameAnalyzer );

        Visitor<Void> treePrinter = new TreePrinter();
        toorlaASTCode.accept( treePrinter );
    }
}
