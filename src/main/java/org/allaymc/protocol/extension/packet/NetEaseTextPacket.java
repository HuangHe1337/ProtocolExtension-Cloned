package org.allaymc.protocol.extension.packet;

import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

@Getter
@Setter
public class NetEaseTextPacket extends TextPacket {
    private String unknown = "";
}
