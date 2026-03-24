package org.allaymc.protocol.extension.codec.v686.serializer;

import io.netty.buffer.ByteBuf;
import org.allaymc.protocol.extension.packet.NetEaseTextPacket;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v685.serializer.TextSerializer_v685;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

public class TextSerializer_v686_NetEase extends TextSerializer_v685 {

    public static final TextSerializer_v686_NetEase INSTANCE = new TextSerializer_v686_NetEase();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        super.serialize(buffer, helper, packet);

        var type = packet.getType();
        if (type == TextPacket.Type.CHAT || type == TextPacket.Type.POPUP) {
            String unknown = packet instanceof NetEaseTextPacket netEase ? netEase.getUnknown() : "";
            helper.writeString(buffer, unknown);
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        super.deserialize(buffer, helper, packet);

        var type = packet.getType();
        if (type == TextPacket.Type.CHAT || type == TextPacket.Type.POPUP) {
            String unknown = helper.readString(buffer);
            if (packet instanceof NetEaseTextPacket netEase) {
                netEase.setUnknown(unknown);
            }
        }
    }
}
