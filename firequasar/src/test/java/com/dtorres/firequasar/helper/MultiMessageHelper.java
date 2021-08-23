package com.dtorres.firequasar.helper;

import java.util.Arrays;

public class MultiMessageHelper {

  public static String[] getKenobiMessage() {
    String[] kenobi = {"este", "", "", "mensaje", ""};
    return kenobi;
  }

  public static String[] getSkywalkerMessage() {
    String[] skywalker = {"", "es", "", "", "secreto"};
    return skywalker;
  }

  public static String[] getSatoMessage() {
    String[] sato = {"este", "", "un", "", "",""};
    return sato;
  }

  public static String[][] getMiltidimiensional() {
    return Arrays.asList(getKenobiMessage(), getSkywalkerMessage(), getSatoMessage())
                 .stream().toArray(String[][]::new);
  }
}
