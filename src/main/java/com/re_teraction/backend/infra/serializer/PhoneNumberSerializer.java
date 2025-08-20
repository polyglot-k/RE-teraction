package com.re_teraction.backend.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.re_teraction.backend.domain.user.vo.PhoneNumber;
import java.io.IOException;

public class PhoneNumberSerializer extends StdSerializer<PhoneNumber> {

    public PhoneNumberSerializer() {
        super(PhoneNumber.class);
    }

    @Override
    public void serialize(PhoneNumber value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeString(value.getValue());
    }
}