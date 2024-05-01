create database UMC4;
use UMC4;


CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    gender ENUM('Male', 'Female', 'Not Choose'),
    age TINYINT UNSIGNED,
    email VARCHAR(50),
    password VARCHAR(255),
    normal_address VARCHAR(100),
    specific_address VARCHAR(100),
    member_status ENUM('Active', 'Inactive'),
    inactive_date DATETIME,
    social_media_type ENUM('kakao', 'naver', 'apple', 'google'),
    created_at DATETIME(6),
    updated_at DATETIME(6),
    points INT
);

CREATE TABLE terms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    content TEXT,
    is_optional BOOLEAN,
    created_at DATETIME(6),
    updated_at DATETIME(6)
);

CREATE TABLE member_agree (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    terms_id BIGINT,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (terms_id) REFERENCES terms(id)
);

CREATE TABLE region (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    region_name VARCHAR(50),
    created_at DATETIME(6),
    updated_at DATETIME(6)
);

CREATE TABLE store (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    region_id BIGINT,
    store_name VARCHAR(100),
    store_address VARCHAR(100),
    store_score int,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    FOREIGN KEY (region_id) REFERENCES region(id)
);

CREATE TABLE mission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    store_id BIGINT,
    reward_points INT,
    deadline DATETIME,
    mission_description TEXT,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    FOREIGN KEY (store_id) REFERENCES store(id)
);

CREATE table member_mission (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    mission_id BIGINT,
    mission_status ENUM('Ongoing', 'Completed', 'Failed'),
    created_at DATETIME(6),
    updated_at DATETIME(6),
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (mission_id) REFERENCES mission(id)
);

CREATE TABLE review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    store_id BIGINT,
    review_content TEXT,
    review_score int,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (store_id) REFERENCES store(id)
);

CREATE TABLE review_image (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    review_id BIGINT,
    image_url TEXT,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    FOREIGN KEY (review_id) REFERENCES review(id)
);