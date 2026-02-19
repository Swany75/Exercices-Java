/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercici01.fitxerambexcepcions;

/**
 *
 * @author swany
 */

class CE {

    public static class FileDoesNotExistException extends Exception {
        public FileDoesNotExistException(String msg) {
            super(msg);
        }
    }

    public static class MaxAttemptsExceededException extends RuntimeException {
        public MaxAttemptsExceededException(String msg) {
            super(msg);
        }
    }

    public static class EmptyFirstLineException extends Exception {
        public EmptyFirstLineException(String msg) {
            super(msg);
        }
    }

    public static class SeparatorNotFoundException extends Exception {
        public SeparatorNotFoundException(String msg) {
            super(msg);
        }
    }

    public static class InvalidFormatException extends Exception {
        public InvalidFormatException(String msg) {
            super(msg);
        }
    }
}
