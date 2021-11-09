package com.example.dictionary.API;

import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.Voice;

public class TextToSpeech {
  public static void Speak(String input) {
    System.setProperty(
        "freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    Voice voice = VoiceManager.getInstance().getVoice("kevin16");

    if (voice != null) {
      voice.allocate();
      voice.speak(input);
      voice.deallocate();
    } else {
      System.err.println("Error When Trying To Speak");
    }
  }

  // Example How To Use
/*
  public static void main(String[] args) {
    Speak("die");
  }
 */

}
