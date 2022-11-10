package io.gitlab.mihajlonesic.numistagraphql.resolver.directive;

import graphql.language.StringValue;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLFieldsContainer;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CaseConvertDirective implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLArgument argument = environment.getDirective().getArgument("to");
        StringValue sv = (StringValue) argument.getArgumentValue().getValue();
        String targetCase = sv.getValue();

        GraphQLFieldDefinition field = environment.getElement();
        GraphQLFieldsContainer parentType = environment.getFieldsContainer();

        DataFetcher<?> originalFetcher = environment.getCodeRegistry().getDataFetcher(parentType, field);
        DataFetcher<?> dataFetcher = DataFetcherFactories.wrapDataFetcher(originalFetcher, (dataFetchingEnvironment, o) -> {
            String word = null;
            try {
                word = o.toString();
            }
            catch (Exception ex) {
                System.out.println("Cannot cast " + o.getClass() + " to String");
            }

            if (word != null) {
                switch (targetCase) {
                    case "upper":
                        return word.toUpperCase();
                    case "lower":
                        return word.toLowerCase();
                    case "capital":
                        word = word.replaceAll("[-._]", " ");
                        String first = word.substring(0, 1);
                        String rem = word.substring(1);
                        return first.toUpperCase() + rem.toLowerCase();
                    case "capital_all":
                        String[] words = word.replaceAll("[-._]", " ").split(" ");
                        return Arrays.stream(words).map(w -> {
                            String f = w.substring(0, 1);
                            String r = w.substring(1);
                            return f.toUpperCase() + r.toLowerCase();
                        }).collect(Collectors.joining(" "));
                    default:
                        return word;
                }
            }
            return o;
        });

        environment.getCodeRegistry().dataFetcher(parentType, field, dataFetcher);
        return field;
    }
}
