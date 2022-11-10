package io.gitlab.mihajlonesic.numistagraphql.entity.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Composition {
    ALUMINIUM("Aluminium", Tag.NON_PRECIOUS, Tag.ALUMINIUM),
    ALUMINIUM_BRASS("Aluminium-brass", Tag.NON_PRECIOUS, Tag.ALUMINIUM, Tag.BRASS),
    ALUMINIUM_BRONZE("Aluminium-bronze", Tag.NON_PRECIOUS, Tag.ALUMINIUM, Tag.BRONZE),
    ALUMINIUM_MAGNESIUM("Aluminium-magnesium", Tag.NON_PRECIOUS, Tag.ALUMINIUM, Tag.MAGNESIUM),
    ALUMINIUM_NICKEL_BRONZE("Aluminium-nickel-bronze", Tag.NON_PRECIOUS, Tag.ALUMINIUM, Tag.NICKEL, Tag.BRONZE),
    ALUMINIUM_ZINC_BRONZE("Aluminium-zinc-bronze", Tag.NON_PRECIOUS, Tag.ALUMINIUM, Tag.ZINC, Tag.BRONZE),
    BILLON("Billon", Tag.NON_PRECIOUS),
    BIMETALLIC("Bimetallic", Tag.OTHER),
    BRASS("Brass", Tag.NON_PRECIOUS, Tag.BRASS),
    BRASS_PLATED_STEEL("Brass plated steel", Tag.NON_PRECIOUS, Tag.BRASS, Tag.STEEL),
    BRONZE("Bronze", Tag.NON_PRECIOUS, Tag.BRONZE),
    BRONZE_NICKEL("Bronze-nickel", Tag.NON_PRECIOUS, Tag.BRONZE, Tag.NICKEL),
    CHROMIUM("Chromium", Tag.NON_PRECIOUS),
    CLAY("Clay", Tag.NON_METALLIC),
    COPPER("Copper", Tag.NON_PRECIOUS, Tag.COPPER),
    COPPER_ALUMINIUM("Copper-aluminium", Tag.NON_PRECIOUS, Tag.COPPER, Tag.ALUMINIUM),
    COPPER_ALUMINIUM_NICKEL("Copper-aluminium-nickel", Tag.NON_PRECIOUS, Tag.COPPER, Tag.ALUMINIUM, Tag.NICKEL),
    COPPER_CLAD_STEEL("Copper clad steel", Tag.NON_PRECIOUS, Tag.COPPER, Tag.STEEL),
    COPPER_NICKEL("Copper-nickel", Tag.NON_PRECIOUS, Tag.COPPER, Tag.NICKEL),
    COPPER_NICKEL_IRON("Copper-nickel-iron", Tag.NON_PRECIOUS, Tag.COPPER, Tag.NICKEL, Tag.IRON),
    COPPER_PLATED_STEEL("Copper plated steel", Tag.NON_PRECIOUS, Tag.COPPER, Tag.STEEL),
    ELECTRUM("Electrum", Tag.PRECIOUS, Tag.GOLD, Tag.SILVER),
    GLIDING_METAL("Gliding metal", Tag.NON_PRECIOUS, Tag.COPPER),
    GOLD("Gold", Tag.PRECIOUS, Tag.GOLD),
    GOLD_PLATED_COPPER("Gold plated copper", Tag.PRECIOUS, Tag.GOLD, Tag.COPPER),
    IRIDIUM("Iridium", Tag.PRECIOUS),
    IRON("Iron", Tag.NON_PRECIOUS, Tag.IRON),
    LEAD("Lead", Tag.NON_PRECIOUS, Tag.LEAD),
    LEADED_COPPER("Leaded copper", Tag.NON_PRECIOUS, Tag.LEAD, Tag.COPPER),
    MAGNESIUM("Magnesium", Tag.NON_PRECIOUS, Tag.MAGNESIUM),
    MAGNESIUM_BRASS("Magnesium-brass", Tag.NON_PRECIOUS, Tag.MAGNESIUM, Tag.BRASS),
    NICKEL("Nickel", Tag.NON_PRECIOUS, Tag.NICKEL),
    NICKEL_BRASS("Nickel-brass", Tag.NON_PRECIOUS, Tag.NICKEL, Tag.BRASS),
    NICKEL_STEEL("Nickel-steel", Tag.NON_PRECIOUS, Tag.NICKEL, Tag.STEEL),
    NICKEL_CLAD_STEEL("Nickel clad steel", Tag.NON_PRECIOUS, Tag.NICKEL, Tag.STEEL),
    NICKEL_PLATED_STEEL("Nickel plated steel", Tag.NON_PRECIOUS, Tag.NICKEL, Tag.STEEL),
    NICKEL_SILVER("Nickel-silver", Tag.PRECIOUS, Tag.NICKEL, Tag.SILVER),
    NICKEL_ZINC("Nickel-zinc", Tag.NON_PRECIOUS, Tag.NICKEL, Tag.ZINC),
    NIOBIUM("Niobium", Tag.PRECIOUS),
    NORDIC_GOLD("Nordic gold", Tag.NON_PRECIOUS, Tag.COPPER),
    ORICHALCUM("Oruchalcum", Tag.PRECIOUS),
    PALLADIUM("Palladium", Tag.PRECIOUS),
    PLASTIC("Plastic", Tag.NON_METALLIC),
    PLATINUM("Platinum", Tag.PRECIOUS),
    SILVER("Silver", Tag.PRECIOUS, Tag.SILVER),
    SILVER_PLATED_NICKEL("Silver plated nickel", Tag.PRECIOUS, Tag.SILVER, Tag.NICKEL),
    STAINLESS_STEEL("Stainless steel", Tag.NON_PRECIOUS, Tag.STEEL),
    STEEL("Steel", Tag.NON_PRECIOUS, Tag.STEEL),
    STEEL_PLATED_BRASS("Steel plated brass", Tag.NON_PRECIOUS, Tag.STEEL, Tag.BRASS),
    TIN("Tin", Tag.NON_PRECIOUS, Tag.TIN),
    TIN_BRASS("Tin-brass", Tag.NON_PRECIOUS, Tag.TIN, Tag.BRASS),
    TIN_LEAD("Tin-lead", Tag.NON_PRECIOUS, Tag.TIN, Tag.LEAD),
    TIN_ZINC("Tin-zinc", Tag.NON_PRECIOUS, Tag.TIN, Tag.ZINC),
    TITANIUM("Titanium", Tag.NON_PRECIOUS),
    TRI_METALLIC("Tri-metallic", Tag.OTHER),
    ZINC("Zinc", Tag.NON_PRECIOUS, Tag.ZINC),
    ZINC_ALUMINIUM("Zinc-aluminium", Tag.NON_PRECIOUS, Tag.ZINC, Tag.ALUMINIUM),
    ZINC_CLAD_STEEL("Zinc clad steel", Tag.NON_PRECIOUS, Tag.ZINC, Tag.STEEL),
    UNDEFINED("Undefined", Tag.OTHER);

    private final String displayName;
    private final List<Tag> tags;

    Composition(String displayName, Tag ... materials) {
        this.displayName = displayName;
        this.tags = List.of(materials);
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public enum Tag {
        PRECIOUS,
        NON_PRECIOUS,
        NON_METALLIC,
        OTHER,
        
        ALUMINIUM,
        BRASS,
        BRONZE,
        COPPER,
        GOLD,
        IRON,
        MAGNESIUM,
        LEAD,
        NICKEL,
        SILVER,
        STEEL,
        TIN,
        ZINC;

        public List<Composition> getItems() {
            return Arrays.stream(Composition.values())
                .filter(composition -> composition.getTags().contains(this))
                .collect(Collectors.toList());
        }
    }
}
