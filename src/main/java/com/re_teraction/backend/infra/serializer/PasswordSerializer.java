package com.re_teraction.backend.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.re_teraction.backend.domain.user.vo.Password;
import java.io.IOException;

public class PasswordSerializer extends StdSerializer<Password> {
    public PasswordSerializer() { super(Password.class); }

    @Override
    public void serialize(Password value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeString(value.getValue());
    }
}