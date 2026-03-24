package org.allaymc.protocol.extension.codec.v630;

import org.allaymc.protocol.extension.codec.common.serializer.ConfirmSkinSerializer;
import org.allaymc.protocol.extension.codec.common.serializer.NetEaseJsonSerializer;
import org.allaymc.protocol.extension.codec.common.serializer.PyRpcSerializer;
import org.allaymc.protocol.extension.codec.common.serializer.StoreBuySuccessSerializer;
import org.allaymc.protocol.extension.codec.v630.serializer.PlayerAuthInputSerializer_v630_NetEase;
import org.allaymc.protocol.extension.codec.v630.serializer.TextSerializer_v630_NetEase;
import org.allaymc.protocol.extension.codec.common.serializer.PlayerEnchantOptionsSerializer_v407_NetEase;
import org.allaymc.protocol.extension.packet.ConfirmSkinPacket;
import org.allaymc.protocol.extension.packet.NetEaseJsonPacket;
import org.allaymc.protocol.extension.packet.NetEasePlayerAuthInputPacket;
import org.allaymc.protocol.extension.packet.NetEaseTextPacket;
import org.allaymc.protocol.extension.packet.PyRpcPacket;
import org.allaymc.protocol.extension.packet.StoreBuySuccessPacket;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v575.BedrockCodecHelper_v575;
import org.cloudburstmc.protocol.bedrock.codec.v630.Bedrock_v630;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerEnchantOptionsPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * NetEase variant of the v630 codec.
 *
 * @author daoge_cmd
 */
public class Bedrock_v630_NetEase extends Bedrock_v630 {

    protected static final TypeMap<ContainerSlotType> CONTAINER_SLOT_TYPES = Bedrock_v630.CONTAINER_SLOT_TYPES
            .toBuilder()
            // NetEase: RECIPE_CUSTOM
            .shift(17, 1)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v630.CODEC.toBuilder()
            .raknetProtocolVersion(8)
            .helper(() -> new BedrockCodecHelper_v575(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateFactory(PlayerAuthInputPacket.class, NetEasePlayerAuthInputPacket::new)
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v630_NetEase.INSTANCE)
            .aliasPacket(NetEasePlayerAuthInputPacket.class, PlayerAuthInputPacket.class)
            .updateFactory(TextPacket.class, NetEaseTextPacket::new)
            .updateSerializer(TextPacket.class, TextSerializer_v630_NetEase.INSTANCE)
            .aliasPacket(NetEaseTextPacket.class, TextPacket.class)
            .updateSerializer(PlayerEnchantOptionsPacket.class, PlayerEnchantOptionsSerializer_v407_NetEase.INSTANCE)
            .registerPacket(PyRpcPacket::new, PyRpcSerializer.INSTANCE, 200, PacketRecipient.BOTH)
            .registerPacket(StoreBuySuccessPacket::new, StoreBuySuccessSerializer.INSTANCE, 202, PacketRecipient.BOTH) // TODO: check packet recipient
            .registerPacket(NetEaseJsonPacket::new, NetEaseJsonSerializer.INSTANCE, 203, PacketRecipient.BOTH)
            .registerPacket(ConfirmSkinPacket::new, ConfirmSkinSerializer.INSTANCE, 228, PacketRecipient.CLIENT)
            .build();
}
