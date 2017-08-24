alter table mail_delivery_channels
    add column unsubscribe_link bit(1) DEFAULT b'0';