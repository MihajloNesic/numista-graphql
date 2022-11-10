package io.gitlab.mihajlonesic.numistagraphql.resolver.scalar;

import graphql.language.StringValue;
import graphql.schema.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LongScalarType {

    @Bean
    public GraphQLScalarType longScalar() {
        return GraphQLScalarType.newScalar()
                .name("Long")
                .description("64-bit signed integer")
                .coercing(new Coercing<Long, String>() {
                    @Override
                    public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof Long) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a Long object.");
                        }
                    }

                    @Override
                    public @NotNull Long parseValue(@NotNull Object input) throws CoercingParseValueException {
                        try {
                            if (input instanceof String) {
                                return Long.valueOf((String) input);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (Exception e) {
                            throw new CoercingParseValueException(String.format("Not a valid Long: '%s'.", input), e);
                        }
                    }

                    @Override
                    public @NotNull Long parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                        if (input instanceof StringValue) {
                            try {
                                return Long.valueOf(((StringValue) input).getValue());
                            } catch (Exception e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                })
                .build();
    }
}
