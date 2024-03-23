-- creating database
DROP DATABASE if exists tourist_attractions ;
create database tourist_attractions;
use tourist_attractions;



-- creating tables
create table city (
	city_id varchar(5),
    city_name varchar(30) NOT NULL,
    city_postal_code int UNIQUE NOT NULL,
    
    PRIMARY KEY (city_id)
);

create table attraction (
    attr_id int AUTO_INCREMENT,
    city_id varchar(5) NOT NULL,
    attr_name varchar(60) UNIQUE NOT NULL,
    attr_description varchar(255) NOT NULL,
    
    PRIMARY KEY (attr_id),
    FOREIGN KEY (city_id) REFERENCES city(city_id)
);

create table tag (
	tag_id int AUTO_INCREMENT,
    tag_name varchar(45) UNIQUE NOT NULL,
    PRIMARY KEY (tag_id)
);

create table attraction_tags (
	attr_id int,
    tag_id int,
    CONSTRAINT attr_tag PRIMARY KEY (attr_id, tag_id),
    FOREIGN KEY (attr_id) REFERENCES attraction(attr_id),
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id)
);



-- populating tables

-- city : city_id , city_name , postal_code
INSERT INTO city
VALUES ('HEGØR', 'Helsingør', 3000);
INSERT INTO city
VALUES ('HUMBK', 'Humlebæk', 3050);
INSERT INTO city
VALUES ('ÅLSGD', 'Ålsgårde', 3140);
INSERT INTO city
VALUES ('HELBK', 'Hellebæk', 3150);

-- attraction : *attr_id AUTO* , city_id , attr_name , attr_description
INSERT INTO attraction (city_id, attr_name, attr_description)
VALUES ('HEGØR', 'Kronborg Castle', "An impressive Renaissance castle, famously known as Hamlet's castle. It is a UNESCO World Heritage Site, attracting visitors with its history and architecture.");
INSERT INTO attraction (city_id, attr_name, attr_description)
VALUES ('HEGØR', 'M S Maritime Museum of Denmark', "A maritime museum showcasing Denmark's maritime history. The museum features an impressive collection of ships, models, and artifacts.");
INSERT INTO attraction (city_id, attr_name, attr_description)
VALUES ('HEGØR', 'Helsingør Cathedral (St. Olai Church)', 'A beautiful cathedral, also known as St. Olai Church, dating back to the 13th century. The church boasts impressive architecture and artworks.');
INSERT INTO attraction (city_id, attr_name, attr_description)
VALUES ('HEGØR', 'Kulturværftet', 'A cultural center by the waterfront hosting theater performances, concerts, and art exhibitions. It is a vibrant hub for culture and entertainment.');
INSERT INTO attraction (city_id, attr_name, attr_description)
VALUES ('HEGØR', 'Øresund Aquarium', 'An aquarium focusing on the marine life of the Øresund region. Visitors can explore a wide range of sea creatures and learn about the marine ecosystem.');
INSERT INTO attraction (city_id, attr_name, attr_description)
VALUES ('HUMBK', 'Louisiana Museum of Modern Art', 'An internationally renowned museum of modern and contemporary art. Located by the sea, the museum offers a unique cultural experience with its impressive art collection and scenic surroundings.');
INSERT INTO attraction (city_id, attr_name, attr_description)
VALUES ('HELBK', 'Hammermøllen', 'Hammermøllen is a historic watermill located in Hellebæk. The mill has a fascinating history and beautiful surroundings next to the stream.');
INSERT INTO attraction (city_id, attr_name, attr_description)
VALUES ('ÅLSGD', 'Skibstrup Recycling Center', 'A recycling center located in Ålsgårde, providing the opportunity for proper disposal of waste and recycling. Contribute to environmentally friendly practices by reusing and recycling materials.');

-- tag : *tag_id AUTOL* , tag_name
INSERT INTO tag (tag_name)
VALUES ('art');
INSERT INTO tag (tag_name)
VALUES ('forest');
INSERT INTO tag (tag_name)
VALUES ('education');
INSERT INTO tag (tag_name)
VALUES ('castle');
INSERT INTO tag (tag_name)
VALUES ('nature');
INSERT INTO tag (tag_name)
VALUES ('church');
INSERT INTO tag (tag_name)
VALUES ('recycling');
INSERT INTO tag (tag_name)
VALUES ('history');
INSERT INTO tag (tag_name)
VALUES ('scenic');
INSERT INTO tag (tag_name)
VALUES ('sustainability');
INSERT INTO tag (tag_name)
VALUES ('food');
INSERT INTO tag (tag_name)
VALUES ('aquarium');
INSERT INTO tag (tag_name)
VALUES ('environment');
INSERT INTO tag (tag_name)
VALUES ('museum');
INSERT INTO tag (tag_name)
VALUES ('watermill');
INSERT INTO tag (tag_name)
VALUES ('culture');
INSERT INTO tag (tag_name)
VALUES ('indoors');
INSERT INTO tag (tag_name)
VALUES ('maritime');
INSERT INTO tag (tag_name)
VALUES ('cathedral');
INSERT INTO tag (tag_name)
VALUES ('events');

-- attraction_tags : attr_id , tag_id 
INSERT INTO attraction_tags
VALUES (1, 4);
INSERT INTO attraction_tags
VALUES (1, 8);
INSERT INTO attraction_tags
VALUES (1, 14);
INSERT INTO attraction_tags
VALUES (2, 14);
INSERT INTO attraction_tags
VALUES (2, 18);
INSERT INTO attraction_tags
VALUES (2, 8);
INSERT INTO attraction_tags
VALUES (3, 19);
INSERT INTO attraction_tags
VALUES (3, 6);
INSERT INTO attraction_tags
VALUES (3, 8);
INSERT INTO attraction_tags
VALUES (4, 16);
INSERT INTO attraction_tags
VALUES (4, 1);
INSERT INTO attraction_tags
VALUES (4, 20);
INSERT INTO attraction_tags
VALUES (4, 11);
INSERT INTO attraction_tags
VALUES (4, 17);
INSERT INTO attraction_tags
VALUES (5, 12);
INSERT INTO attraction_tags
VALUES (5, 3);
INSERT INTO attraction_tags
VALUES (5, 17);
INSERT INTO attraction_tags
VALUES (6, 14);
INSERT INTO attraction_tags
VALUES (6, 1);
INSERT INTO attraction_tags
VALUES (6, 9);
INSERT INTO attraction_tags
VALUES (7, 15);
INSERT INTO attraction_tags
VALUES (7, 8);
INSERT INTO attraction_tags
VALUES (7, 9);
INSERT INTO attraction_tags
VALUES (7, 2);
INSERT INTO attraction_tags
VALUES (7, 5);
INSERT INTO attraction_tags
VALUES (8, 7);
INSERT INTO attraction_tags
VALUES (8, 13);
INSERT INTO attraction_tags
VALUES (8, 10);
