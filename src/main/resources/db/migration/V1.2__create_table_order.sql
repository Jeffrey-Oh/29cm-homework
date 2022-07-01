-- order
create table orders
(
    id                bigint auto_increment primary key comment 'ID',
    order_token       varchar(255) not null             comment 'order_token'
);

-- order_items
create table order_items
(
    id              bigint auto_increment primary key comment 'ID',
    order_id        bigint       not null             comment 'order_id',
    order_count     tinyint      not null             comment '주문갯수',
    item_name       varchar(255) not null             comment '상품명',
    item_token      varchar(30)  not null             comment '상품 token',
    item_price      int          not null             comment '상품 가격'
);