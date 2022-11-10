package io.gitlab.mihajlonesic.numistagraphql.entity.domain;

public enum Shape {
    DODECAGONAL("Dodecagonal (12 sided)"),
    HEXAGONAL("Hexagonal (6 sided)"),
    IRREGULAR("Irregular"),
    OCTAGONAL("Octagonal (8 sided)"),
    OVAL("Oval"),
    RECTANGULAR("Rectangular"),
    RHOMBUS("Rhombus"),
    ROUND("Round"),
    ROUND_IRREGULAR("Round (irregular)"),
    ROUND_WITH_A_ROUND_HOLE("Round with a round hole"),
    ROUND_WITH_A_SQUARE_HOLE("Round with a square hole"),
    SCALLOPED_WITH_12_NOTCHES("Scalloped with 12 notches"),
    SCALLOPED_WITH_8_NOTCHES("Scalloped with 8 notches"),
    SCYPHATE("Scyphate"),
    SQUARE("Square"),
    SQUARE_WITH_ROUNDED_CORNERS("Square with rounded corners"),
    OTHER("Other");

    private final String displayName;

    Shape(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
