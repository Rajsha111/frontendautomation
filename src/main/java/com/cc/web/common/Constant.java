package com.cc.web.common;

public class Constant {

    private static final String HEADER_PRIMARY = "Primary";
    private static final String HEADER_SOCIAL = "Social";
    private static final String HEADER_PROMOTIONS = "Promotions";
    public static final String NO_SENDER_MESSAGE_WHEN_ADD = "no sender since its an advertise";
    public static final String EMAIL_NOT_PRESENT = "Given mail index is invalid";


    public enum Header {
        PRIMARY, SOCIAL, PROMOTION;

        @Override
        public String toString() {
            switch (this) {
                case PRIMARY:
                    return HEADER_PRIMARY;

                case SOCIAL:
                    return HEADER_SOCIAL;

                case PROMOTION:
                    return HEADER_PROMOTIONS;

                default:
                    return null;
            }
        }
    }
}
