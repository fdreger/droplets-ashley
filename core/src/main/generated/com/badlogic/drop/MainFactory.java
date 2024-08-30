package com.badlogic.drop;

import net.snowyhollows.bento.Bento;
import net.snowyhollows.bento.BentoFactory;

public enum MainFactory implements BentoFactory<Main> {
  IT;

  public Main createInContext(Bento bento) {
    return new Main();
  }
}
