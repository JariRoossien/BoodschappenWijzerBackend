package nl.dizmizzer.smc.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.dizmizzer.smc.core.entity.SmProductGroup;
import org.apache.commons.text.similarity.FuzzyScore;

import java.util.*;

public class SmProductIndex {

    Map<TermToken, Set<SmProductGroup>> reverseIdf = new HashMap<>();
    FuzzyScore fuzzyScore = new FuzzyScore(Locale.getDefault());

    public void registerNewProduct(SmProductGroup product) {
        if (product.getBrand() != null) {
            TermToken brandToken = new TermToken(product.getBrand().toLowerCase(), 10);
            registerProductAtToken(brandToken, product);
        }
        if (product.getName() != null) {
            TermToken descriptionToken = new TermToken(product.getName().toLowerCase(), 15);
            registerProductAtToken(descriptionToken, product);
        }

        if (product.getRawunitInfo() != null) {
            TermToken unitSizeToken = new TermToken(product.getRawunitInfo().toLowerCase(), 10);
            registerProductAtToken(unitSizeToken, product);
        }



    }

    private void registerProductAtToken(TermToken token, SmProductGroup product) {
        if (!reverseIdf.containsKey(token)) {
            reverseIdf.put(token, new HashSet<>());
        }
        reverseIdf.get(token).add(product);
    }

    public List<SmProductGroup> lookupQuery(String query) {

        Map<SmProductGroup, Integer> queryMap = new HashMap<>();

        for (String token: tokenize(query)) {
            for (TermToken term: reverseIdf.keySet()) {
                int score = fuzzyScore.fuzzyScore(term.token, token);

                //
                if (score > 2) {
                    reverseIdf.get(term).forEach(product -> queryMap.put(product,
                            queryMap.getOrDefault(product, 0) + (score * term.weight))
                    );
                }
            }
        }

        return queryMap.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }

    // For now keep it very basic with splitting on spaces.
    public String[] tokenize(String query) {
        return query.toLowerCase().split("\\s+");
    }

    @Data
    @AllArgsConstructor
    private static class TermToken {
        String token;
        int weight;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TermToken termToken = (TermToken) o;
            return weight == termToken.weight && Objects.equals(token, termToken.token);
        }

        @Override
        public int hashCode() {
            return Objects.hash(token, weight);
        }
    }
}
