// author name - Oghenefeiro Isaacs Anigboro
/*pre-condition - wordList contains words with only 5 letters
 **/
/*post-condition - the program compares the word the user inputs to the randomWord selected by the program.
if the words are the same, the program ends. the users are allowed a maximum of 10 guesses. after the guesses are used, the program tells the user the correct word and ends.
**/

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    
    //creating a scanner object
    Scanner sam = new Scanner(System.in);

    //creating the wordList array
    String[] wordList = { "games", "races", "judge", "field", "court" };
    
    //creating a variable to select a random word form the array and creating the variable that keeps track of the number of guesses the user has.
    int randomWord = (int) ((Math.random() * 5) + 0);
    int totalGuesses = 10;

    //selects the random word from the list and stores it in a variable them gets the users input and stores that also.
    String pickedWord = wordList[randomWord];
    System.out.println("Welcome to my Wordle game, please guess a random 5 letter word relating to sports");
    String userGuess = sam.nextLine();

    //ensures that the user inputs a word with exactly 5 letters to avoid an error.
    while (userGuess.length() != 5) {
      System.out.println("Please enter a word with only 5 letters");
      userGuess = sam.nextLine();
    }

    //creates the lists that stores each letter;
    String[] letterList = new String[5];
    String[] userLetterList = new String[5];

    //call to the list converter method
    listConv(pickedWord, letterList);
    listConv(userGuess, userLetterList);

    //checks if the first word the user enters is the correct word, else it calls the wordChecker method
    if (userGuess.equalsIgnoreCase(pickedWord)) {
      System.out.println("congrats, you guessed the right word");
    } else {
      wordChecker(letterList, userLetterList);
    }
    //reduces the users remaining guesses by 1
    totalGuesses--;

    //loops through the code that checks if the user entered the correct word until the user runs out of guesses or they enter the correct word.
    while (!(userGuess.equalsIgnoreCase(pickedWord)) && totalGuesses > 0) {

      System.out.println("You have " + totalGuesses + " guesses left");
      userGuess = sam.nextLine();

      while (userGuess.length() != 5) {
        System.out.println("Please enter a word with only 5 letters");
        userGuess = sam.nextLine();
      }
      listConv(userGuess, userLetterList);

      if (userGuess.equalsIgnoreCase(pickedWord)) {
        System.out.println("congrats, you guessed the right word");
      } else {
        wordChecker(letterList, userLetterList);
      }

      totalGuesses--;
    }

    // tells the user the correct word if they have used all their guesses. 
    if(totalGuesses == 0){
      System.out.println("Nice try, the correct word is " + pickedWord);
    }

    //closes the scanner once the user gets the correct word or they exceeded their guess limit.
    sam.close();
  }

  //method that converts the pickedWord and userGuess word to a list. it stores each letter in each position of the list. 
  public static void listConv(String word, String[] list) {

    for (int i = 0; i < word.length(); i++) {
      list[i] = word.substring(i, i + 1);
    }

  }

  //method that tells the user if the letters in the word they entered are in the program selected word and in the right spot, else it calls the letterChecker method.
  public static void wordChecker(String[] list, String[] userList) {

    for (int i = 0; i < list.length; i++) {
      if (!(userList[i].equalsIgnoreCase(list[i]))) {
        letterChecker(list, userList[i]);
      }
      if (userList[i].equalsIgnoreCase(list[i])) {
        System.out.println(userList[i] + " is in the word and in the right spot");
      }
    }

  }

  //method that tells the user if a letter in the word they entered is in the program selected word or not.
  public static void letterChecker(String[] list, String letter) {

    int findval = 0;

    for (int i = 0; i < list.length; i++) {
      if (list[i].equalsIgnoreCase(letter)) {
        System.out.println(letter + " is in the word but not in the right spot");
        findval++;
      }
    }

    if (findval == 0) {
      System.out.println(letter + " is not in the word");
    }

  }
}