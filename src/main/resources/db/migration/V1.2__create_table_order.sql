-- order
create table orders
(
    id                bigint auto_increment primary key comment 'ID',
    order_token       varchar(255) not null             comment 'order_token'
) comment 'orders' charset = utf8mb4;

-- order_items
create table order_items
(
    id              bigint auto_increment primary key comment 'ID',
    order_id        bigint       not null             comment 'order_id',
    order_count     tinyint      not null             comment '주문갯수',
    item_name       varchar(255) not null             comment '상품명',
    item_token      varchar(30)  not null             comment '상품 token',
    item_price      int(11)      not null             comment '상품 가격'
) comment 'order_items' charset = utf8mb4;