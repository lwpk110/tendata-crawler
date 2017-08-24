
/**
 * Created by jack.qiao on 2017/6/27.
 */

alter table mail_delivery_channel_nodes
    add column `deleted` bit(1) DEFAULT b'0';

alter table mail_agent_domains
    add column `ip_info` varchar(255),
    add column `deleted` bit(1) DEFAULT b'0';

alter table mail_agent_domains
    add constraint FK_c7c220cd535d53dc83d7beb3e foreign key (channel_id)
    references mail_delivery_channels (id);

alter table mail_delivery_channels
    add column `supplier` varchar(255),
    add column `max_num_daily` int(11) DEFAULT 0,
    add column `surplus_num` int(11) DEFAULT 0,
    add column `deleted` bit(1) DEFAULT b'0';