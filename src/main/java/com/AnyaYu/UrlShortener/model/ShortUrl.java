package com.AnyaYu.UrlShortener.model;

import com.AnyaYu.UrlShortener.exception.UrlException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShortUrl {

    private static final List<Character> CHARS = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9');

    public static final int SHORT_URL_SIZE = 6;

    private final String stringId;

    public ShortUrl() {
        stringId = getRandomHash();
    }

    public ShortUrl(String stringId) {

        List<Integer> intList = CHARS.stream()
                .map(ch -> (int) ch)
                .collect(Collectors.toList());
        boolean allMatch = intList.containsAll(stringId.chars().boxed().collect(Collectors.toList()));

        if (!allMatch || stringId.length() != SHORT_URL_SIZE) {
            throw new UrlException("Invalid short url");
        }
        this.stringId = stringId;
    }

    private String getRandomHash() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < SHORT_URL_SIZE; i++) {
            stringBuilder.append(CHARS.get((int) (Math.random() * CHARS.size())));
        }
        return stringBuilder.toString();
    }

    public String getStringId() {
        return stringId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortUrl shortUrl = (ShortUrl) o;
        return stringId.equals(shortUrl.stringId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringId);
    }
}
