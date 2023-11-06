PGDMP     !                
    {            ScheduleBot    10.23    10.23 /    !           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            "           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            #           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            $           1262    24970    ScheduleBot    DATABASE     �   CREATE DATABASE "ScheduleBot" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE "ScheduleBot";
             root    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            %           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            &           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    25047    courses    TABLE     U   CREATE TABLE public.courses (
    number integer NOT NULL,
    id bigint NOT NULL
);
    DROP TABLE public.courses;
       public         root    false    3            �            1259    25055    courses_groups    TABLE     f   CREATE TABLE public.courses_groups (
    courses_id bigint NOT NULL,
    groups_id bigint NOT NULL
);
 "   DROP TABLE public.courses_groups;
       public         root    false    3            �            1259    25045    courses_id_seq    SEQUENCE     w   CREATE SEQUENCE public.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.courses_id_seq;
       public       root    false    3    197            '           0    0    courses_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id;
            public       root    false    196            �            1259    25060    groups    TABLE     T   CREATE TABLE public.groups (
    number integer NOT NULL,
    id bigint NOT NULL
);
    DROP TABLE public.groups;
       public         root    false    3            �            1259    25058    groups_id_seq    SEQUENCE     v   CREATE SEQUENCE public.groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.groups_id_seq;
       public       root    false    3    200            (           0    0    groups_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.groups_id_seq OWNED BY public.groups.id;
            public       root    false    199            �            1259    25070    subjects    TABLE     �  CREATE TABLE public.subjects (
    begintime time(6) without time zone NOT NULL,
    endtime time(6) without time zone NOT NULL,
    numeratorordenominator integer NOT NULL,
    subgroup integer NOT NULL,
    course_id bigint NOT NULL,
    group_id bigint NOT NULL,
    id bigint NOT NULL,
    info character varying(255) NOT NULL,
    lecturer character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    weekday character varying(255) NOT NULL
);
    DROP TABLE public.subjects;
       public         root    false    3            �            1259    25068    subjects_id_seq    SEQUENCE     x   CREATE SEQUENCE public.subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.subjects_id_seq;
       public       root    false    3    202            )           0    0    subjects_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.subjects_id_seq OWNED BY public.subjects.id;
            public       root    false    201            �            1259    25081    users    TABLE     �   CREATE TABLE public.users (
    subgroup integer NOT NULL,
    course_id bigint NOT NULL,
    group_id bigint NOT NULL,
    id bigint NOT NULL,
    telegram_id bigint
);
    DROP TABLE public.users;
       public         root    false    3            �            1259    25079    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public       root    false    204    3            *           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
            public       root    false    203            �
           2604    25050 
   courses id    DEFAULT     h   ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);
 9   ALTER TABLE public.courses ALTER COLUMN id DROP DEFAULT;
       public       root    false    196    197    197            �
           2604    25063 	   groups id    DEFAULT     f   ALTER TABLE ONLY public.groups ALTER COLUMN id SET DEFAULT nextval('public.groups_id_seq'::regclass);
 8   ALTER TABLE public.groups ALTER COLUMN id DROP DEFAULT;
       public       root    false    199    200    200            �
           2604    25073    subjects id    DEFAULT     j   ALTER TABLE ONLY public.subjects ALTER COLUMN id SET DEFAULT nextval('public.subjects_id_seq'::regclass);
 :   ALTER TABLE public.subjects ALTER COLUMN id DROP DEFAULT;
       public       root    false    202    201    202            �
           2604    25084    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public       root    false    204    203    204                      0    25047    courses 
   TABLE DATA               -   COPY public.courses (number, id) FROM stdin;
    public       root    false    197   �1                 0    25055    courses_groups 
   TABLE DATA               ?   COPY public.courses_groups (courses_id, groups_id) FROM stdin;
    public       root    false    198   
2                 0    25060    groups 
   TABLE DATA               ,   COPY public.groups (number, id) FROM stdin;
    public       root    false    200   02                 0    25070    subjects 
   TABLE DATA               �   COPY public.subjects (begintime, endtime, numeratorordenominator, subgroup, course_id, group_id, id, info, lecturer, title, weekday) FROM stdin;
    public       root    false    202   W2                 0    25081    users 
   TABLE DATA               O   COPY public.users (subgroup, course_id, group_id, id, telegram_id) FROM stdin;
    public       root    false    204   �5       +           0    0    courses_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.courses_id_seq', 1, false);
            public       root    false    196            ,           0    0    groups_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.groups_id_seq', 1, false);
            public       root    false    199            -           0    0    subjects_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.subjects_id_seq', 1, false);
            public       root    false    201            .           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
            public       root    false    203            �
           2606    25054    courses courses_number_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_number_key UNIQUE (number);
 D   ALTER TABLE ONLY public.courses DROP CONSTRAINT courses_number_key;
       public         root    false    197            �
           2606    25052    courses courses_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.courses DROP CONSTRAINT courses_pkey;
       public         root    false    197            �
           2606    25067    groups groups_number_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_number_key UNIQUE (number);
 B   ALTER TABLE ONLY public.groups DROP CONSTRAINT groups_number_key;
       public         root    false    200            �
           2606    25065    groups groups_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.groups DROP CONSTRAINT groups_pkey;
       public         root    false    200            �
           2606    25078    subjects subjects_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.subjects DROP CONSTRAINT subjects_pkey;
       public         root    false    202            �
           2606    25086    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public         root    false    204            �
           2606    25088    users users_telegram_id_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_telegram_id_key UNIQUE (telegram_id);
 E   ALTER TABLE ONLY public.users DROP CONSTRAINT users_telegram_id_key;
       public         root    false    204            �
           2606    25089 *   courses_groups fk5agt7oocj3mxonovxgg548w0p    FK CONSTRAINT     �   ALTER TABLE ONLY public.courses_groups
    ADD CONSTRAINT fk5agt7oocj3mxonovxgg548w0p FOREIGN KEY (groups_id) REFERENCES public.groups(id);
 T   ALTER TABLE ONLY public.courses_groups DROP CONSTRAINT fk5agt7oocj3mxonovxgg548w0p;
       public       root    false    2704    198    200            �
           2606    25109 !   users fk94cc4mtujq4nljbmtf8ijqf4r    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk94cc4mtujq4nljbmtf8ijqf4r FOREIGN KEY (course_id) REFERENCES public.courses(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fk94cc4mtujq4nljbmtf8ijqf4r;
       public       root    false    204    197    2700            �
           2606    25104 $   subjects fk97opnpttu556n9ep4ful6vq2h    FK CONSTRAINT     �   ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT fk97opnpttu556n9ep4ful6vq2h FOREIGN KEY (group_id) REFERENCES public.groups(id);
 N   ALTER TABLE ONLY public.subjects DROP CONSTRAINT fk97opnpttu556n9ep4ful6vq2h;
       public       root    false    202    2704    200            �
           2606    25114 !   users fkemfuglprp85bh5xwhfm898ysc    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkemfuglprp85bh5xwhfm898ysc FOREIGN KEY (group_id) REFERENCES public.groups(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fkemfuglprp85bh5xwhfm898ysc;
       public       root    false    204    200    2704            �
           2606    25094 *   courses_groups fkk4926qwwxvp6bsm96ela4w3mp    FK CONSTRAINT     �   ALTER TABLE ONLY public.courses_groups
    ADD CONSTRAINT fkk4926qwwxvp6bsm96ela4w3mp FOREIGN KEY (courses_id) REFERENCES public.courses(id);
 T   ALTER TABLE ONLY public.courses_groups DROP CONSTRAINT fkk4926qwwxvp6bsm96ela4w3mp;
       public       root    false    198    2700    197            �
           2606    25099 $   subjects fkr4k4crqhj5ojibxp458ndbywv    FK CONSTRAINT     �   ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT fkr4k4crqhj5ojibxp458ndbywv FOREIGN KEY (course_id) REFERENCES public.courses(id);
 N   ALTER TABLE ONLY public.subjects DROP CONSTRAINT fkr4k4crqhj5ojibxp458ndbywv;
       public       root    false    2700    197    202               (   x��A 0��w+fw� 3�cI&iQ^�M;�����D�            x�3��2�4����� ��            x����4�4����� 
         I  x��WMo�@=o~E��A�]۱}�h(�m�����[�r(*RK�R�R!���-I�&����[����8������{�3ov���H:��0.#ǋ��,ğ�.�c��s�MC�N���u�+{�xme�Y���E|�LV����R}i8�i��Զ~ӣK�iDF?�'t���Fn��#��s�w�D�d��4L�3�.����F1ڤ�W@s/�IH�Ф���$��&�ө:�8����i���kRߟ��7��O��4� �1kI/O���-������K�v�X�O޼~��e)����2IpϤj(p���TqW����vFW�x�{S��@��(�;����L���'�Vu���|����꼺�m�AQ�(��/�4@�\1:�iǺ쌾�� ӕz���~앖0�1�	�&��sӫ����|�^�V/E�̕�#�]T�c�wB�9�L����*D��(`��}G��q��o����\B�i���4i(����i��dQ]��iҰ�����i�����ݍ��0-���m���Va���}&�U>TIʖ.�N<��Fz�"7Iɹ�v�+G��9m�ru6�7����#ZZ�E�
�N�$ӢW�,���[[�pl�Z�5�m��S(�H���9��T�6|��&3��uj����l������3o!#0w�ѯ����� "�j���{�L���O�����!-;��O�6�[ٮ�<8x=S �?\�H��ܶ{*�v�l��#�_bJ��8S���
O>;~�t�
�v8_jK�s|1��?��Ӑ��s��c���\+���h��<�� ���9֣dLyޢ���F��,6�            x������ � �     