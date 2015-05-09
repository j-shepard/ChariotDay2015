package com.chariotsolutions.jshepard;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes({"com.chariotsolutions.jshepard.NoNPE"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Processor extends AbstractProcessor {
  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
    if (!roundEnvironment.processingOver()) {
      annotations
          .stream()
          .map(a -> a.getSimpleName())
          .forEach(System.out::println);
    }
    return true;
  }
}
