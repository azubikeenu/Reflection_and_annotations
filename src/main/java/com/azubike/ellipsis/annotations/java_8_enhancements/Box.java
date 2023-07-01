package com.azubike.ellipsis.annotations.custom;

public class Box< @NonEmpty T> {
    @NonEmpty int size;
    T type;

    public Box(final int size, final T type) {
        this.size = size;
        this.type = type;
    }

    class NestedBox extends Box< @NonEmpty T> {
        public NestedBox(final int size, final T type) {
            super(size, type);
        }
    }
}
