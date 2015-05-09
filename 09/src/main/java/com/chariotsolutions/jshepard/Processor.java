package com.chariotsolutions.jshepard;

import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeTranslator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Processor extends AbstractProcessor {
  private Trees trees;

  @Override
  public void init(ProcessingEnvironment processingEnvironment) {
    this.processingEnv = processingEnvironment;
    this.trees = Trees.instance(processingEnvironment);
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
    if (!roundEnvironment.processingOver()) {
      Set<? extends Element> elements = roundEnvironment.getRootElements();
      elements.forEach(element -> {
        JCTree tree = (JCTree) trees.getTree(element);
        TreeTranslator visitor = new ChariotTranslator();
        tree.accept(visitor);
      });
    }
    return false;
  }

  private static class ChariotTranslator extends TreeTranslator {
    @Override
    public void visitVarDef(JCVariableDecl variableDeclaration) {
      super.visitVarDef(variableDeclaration);
      if (isChariot(variableDeclaration.getType())) {
        System.out.println("variableName:" + variableDeclaration.getName());
      }
    }

    private boolean isChariot(JCTree type) {
      return type.toString().contains("chariot");
    }
  }
}
