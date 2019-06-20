package com.noname.qn.parameters;

import com.noname.qn.service.domain.Playable;
import com.noname.qn.service.gui.Gamable;

public class TextValues {

    public enum Language{
        ENGLISH,FRENCH,SPANISH
    }

    public static final String[] LANGUAGE = new String[]{"Language", "Langue","Idioma"};
    public static final String[] LANGUAGE_NAME = new String[]{"English", "Français","Español"};
    public static final String[] APP_NAME = new String[]{"Quantic Nightmare", "Quantic Nightmare", "Quantic Nightmare"};

    public static final String[] ILLEGAL_LEVEL_INSERTION_EXCEPTION_MESSAGE =
            new String[]{"Illegal Level Insertion at position",
                        "Insertion Illégale du niveau à la position",
                        "Illegal inserción de nivel en la posición"};
    public static final String[] OUT_OF_BOUNDS_LEVEL_INSERTION_EXCEPTION_MESSAGE1 =
            new String[]{"OutOfBounds Insertion in Level",
            "Insertion hors des limites du level",
                    "Inserción fuera de limites de nivel"};
    public static final String[] POSITION_EXCEPTION_MESSAGE =
            new String[]{" at position"," à la position"," en la posición"};
    public static final String[] DUPLICATED_LEVEL_INSERTION_EXCEPTION_MESSAGE =
            new String[]{"Duplicated Level Insertion ",
                    "Insertion de niveau dupliquée ",
                    "Inserción de nivel duplicada "};
    public static final String[] LEVEL1_NAME =
            new String[]{"Tutorial", "Tutoriel","Tutorial"};
    public static final String[] LEVEL2_NAME =
            new String[]{"Unknown", "Anonyme","Anónimo"};
    public static final String[] LEVEL3_NAME =
            new String[]{"Nightmare", "Cauchemar","Pesadilla"};
    public static final String[] LEVEL4_NAME =new String[]{"E=MC2", "E=MC2", "E=MC2"};
    public static final String[] UNKNOWN_LEVEL_EXCEPTION_MESSAGE =
            new String[]{"Unknown Level", "Niveau Inconnu", "Nivel Desconocido"};
    public static final String[] BEST_TRY = new String[]{"Best Try", "Meilleur Résultat", "Mejor Intento"};
    public static final String[] MOVES = new String[]{"moves", "coups", "movidas"};
    public static final String[] GAMEOVER = new String[]{"GameOver", "GameOver", "GameOver"};
    public static final String[] GAMEOVER_MESSAGE =
            new String[]{"Your try finishes in state",
                    "Votre tour se termine en état",
                    "Tu intento se termina en estado"};
    public static final String[] RETRY = new String[]{"Retry", "Réessayer", "Reintentar"};
    public static final String[] BONUS = new String[]{"Bonus", "Bonus", "Bonus"};
    public static final String[] QUIT = new String[]{"Quit", "Quitter", "Salir"};
    public static final String[] BACK = new String[]{"Back", "Retour", "Atrás"};

    //Main Menu
    public static final String[] WELCOME = new String[]{"Welcome to ", "Bienvenu à ", "Bienvenido a "};
    public static final String[] PLAY = new String[]{"Play", "Jouer", "Jugar"};
    public static final String[] OPTION = new String[]{"Options", "Options", "Opciones"};
    public static final String[] CONFIRM = new String[]{"Are you Sure?", "Etes-vous sûr?", "Seguro?"};
    public static final String[] YES = new String[]{"Yes", "Oui", "Si"};
    public static final String[] NO = new String[]{"No", "Non", "No"};

    //Option Menu
    public static final String[] SOUND = new String[]{"Music", "Musique", "Musica"};
    public static final String[] EFFECT = new String[]{"Effects", "Effets", "Efectos"};
    public static final String[] ENABLE = new String[]{"Enable", "Activé", "Activado"};
    public static final String[] DISABLE = new String[]{"Disable", "Désactivé", "Desactivado"};

    //Stage
    public static final String[] CHOOSE = new String[]{"Choose your Nightmare",
             "Choisis ton Cauchemar", "Elije tu Pesadilla"};
    public static final String[] LEVEL_BROKEN = new String[]{"This level is broken !!!",
            "Ce niveau a un problème", "Este nivel tiene un problema"};
    public static final String[] LEVEL_NOT_AVAILABLE = new String[]{"This level will be soon available !!!",
            "Ce niveau sera bientôt disponible", "Este nivel estará pronto disponible"};
    public static final String[] WIN = new String[]{"Win","Victoire","Victoria"};
    public static final String[] LOOSE = new String[]{"Loose","Perdu","Perdido"};
    public static final String[] CONTINUE = new String[]{"Continue","Continuer","Continuar"};
    private TextValues(){}
    public static String getStateString(Playable.State st,int language){
        switch (st){
            case WIN:
                return TextValues.WIN[language];
            case LOOSE:
                return TextValues.LOOSE[language];
            case CONTINUE:
                return TextValues.CONTINUE[language];
        }
        return "";
    }

    public static final String[] EASY =
            new String[]{"Easy", "Facile","Fácil"};
    public static final String[] NORMAL =
            new String[]{"Normal", "Normal","Normal"};
    public static final String[] NIGHTMARE =
            new String[]{"Nightmare", "Cauchemar","Pesadilla"};
    public static String getDifficultyString(Gamable.Difficulty d,int language){
        switch (d){
            case EASY:
                return TextValues.EASY[language];
            case NORMAL:
                return TextValues.NORMAL[language];
            case NIGHTMARE:
                return TextValues.NIGHTMARE[language];
        }
        return "";
    }

    public void t(){
        //

        //String[] GAMEOVER = GAMEOVER;
    }
    //String s = TextValues.LEVEL1_NAME[QNPreferences.getLanguage()]

}
