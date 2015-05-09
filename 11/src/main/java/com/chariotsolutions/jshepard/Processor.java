package com.chariotsolutions.jshepard;

import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symbol.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Name;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Processor extends AbstractProcessor {
  private JavacProcessingEnvironment javacProcessingEnvironment;
  private Trees trees;
  private TreeMaker treeMaker;

  @Override
  public void init(ProcessingEnvironment processingEnvironment) {
    this.processingEnv = processingEnvironment;
    this.javacProcessingEnvironment = (JavacProcessingEnvironment)processingEnvironment;
    this.trees = Trees.instance(processingEnvironment);
    this.treeMaker = TreeMaker.instance(this.javacProcessingEnvironment.getContext());
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

  private class ChariotTranslator extends TreeTranslator {
    @Override
    public void visitVarDef(JCVariableDecl variableDeclaration) {
      super.visitVarDef(variableDeclaration);
      if (isChariot(variableDeclaration.getType())) {
        System.out.println("variableName:" + variableDeclaration.getName());
        result = makeStatement(variableDeclaration);
      }
    }

    private boolean isChariot(JCTree type) {
      return type.toString().contains("chariot");
    }

    private JCVariableDecl makeStatement(JCVariableDecl variableDeclaration) {
      JCExpression stringType = treeMaker.Ident((Symbol)getElement(String.class));
      Name variableName = variableDeclaration.getName();
      JCLiteral value = treeMaker.Literal("Chariot is the best!");
      JCModifiers modifiers = treeMaker.Modifiers(0);
      JCVariableDecl newVariableDeclaration = treeMaker.VarDef(modifiers, variableName, stringType, value);
      return newVariableDeclaration;
    }

    private TypeElement getElement(Class<?> javaClass) {
      Optional<? extends Element> matchingElement = getPackageElements(javaClass)
          .stream()
          .filter(e -> e.getSimpleName().toString().equals(javaClass.getSimpleName()))
          .findAny();
      if (!matchingElement.isPresent()) {
        throw new AssertionError("Unable to get " + javaClass);
      }
      return (TypeElement)matchingElement.get();
    }

    private List<? extends Element> getPackageElements(Class<?> javaClass) {
      return processingEnv.getElementUtils().getPackageElement(javaClass.getPackage().getName()).getEnclosedElements();
    }
  }
}
