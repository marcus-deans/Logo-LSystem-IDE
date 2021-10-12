package oolala.view;

/**
 * @author marcusdeans
 * <p>
 * Purpose: Simple enum for the different langauges that can be selected Assumptions:
 * NoneDependencies: None Example Usage: Used in Displays whn selecting which language to displkay
 * Details: None, usage exactly as dictated by command entry pattern (error-checked)
 */
public enum Language {
  ENGLISH("English"),
  FRENCH("French"),
  SPANISH("Spanish");

  private final String languageString;

  /**
   * Create new language
   *
   * @param languageString string stating which language to select
   */
  Language(String languageString) {
    this.languageString = languageString;
  }
}