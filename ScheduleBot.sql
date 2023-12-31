PGDMP                      
    {            tg_bot    15.4    15.4 +    )           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            *           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            +           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ,           1262    16472    tg_bot    DATABASE     z   CREATE DATABASE tg_bot WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE tg_bot;
                root    false            �            1259    16473    courses    TABLE     U   CREATE TABLE public.courses (
    number integer NOT NULL,
    id bigint NOT NULL
);
    DROP TABLE public.courses;
       public         heap    root    false            �            1259    16476    courses_groups    TABLE     f   CREATE TABLE public.courses_groups (
    courses_id bigint NOT NULL,
    groups_id bigint NOT NULL
);
 "   DROP TABLE public.courses_groups;
       public         heap    root    false            �            1259    16479    courses_id_seq    SEQUENCE     w   CREATE SEQUENCE public.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.courses_id_seq;
       public          root    false    214            -           0    0    courses_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id;
          public          root    false    216            �            1259    16480    groups    TABLE     T   CREATE TABLE public.groups (
    number integer NOT NULL,
    id bigint NOT NULL
);
    DROP TABLE public.groups;
       public         heap    root    false            �            1259    16483    groups_id_seq    SEQUENCE     v   CREATE SEQUENCE public.groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.groups_id_seq;
       public          root    false    217            .           0    0    groups_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.groups_id_seq OWNED BY public.groups.id;
          public          root    false    218            �            1259    16484    subjects    TABLE     �  CREATE TABLE public.subjects (
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
       public         heap    root    false            �            1259    16489    subjects_id_seq    SEQUENCE     x   CREATE SEQUENCE public.subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.subjects_id_seq;
       public          root    false    219            /           0    0    subjects_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.subjects_id_seq OWNED BY public.subjects.id;
          public          root    false    220            �            1259    16490    users    TABLE     �   CREATE TABLE public.users (
    subgroup integer NOT NULL,
    course_id bigint NOT NULL,
    group_id bigint NOT NULL,
    id bigint NOT NULL,
    telegram_id bigint
);
    DROP TABLE public.users;
       public         heap    root    false            �            1259    16493    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          root    false    221            0           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          root    false    222            x           2604    16494 
   courses id    DEFAULT     h   ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);
 9   ALTER TABLE public.courses ALTER COLUMN id DROP DEFAULT;
       public          root    false    216    214            y           2604    16495 	   groups id    DEFAULT     f   ALTER TABLE ONLY public.groups ALTER COLUMN id SET DEFAULT nextval('public.groups_id_seq'::regclass);
 8   ALTER TABLE public.groups ALTER COLUMN id DROP DEFAULT;
       public          root    false    218    217            z           2604    16496    subjects id    DEFAULT     j   ALTER TABLE ONLY public.subjects ALTER COLUMN id SET DEFAULT nextval('public.subjects_id_seq'::regclass);
 :   ALTER TABLE public.subjects ALTER COLUMN id DROP DEFAULT;
       public          root    false    220    219            {           2604    16497    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          root    false    222    221                      0    16473    courses 
   TABLE DATA           -   COPY public.courses (number, id) FROM stdin;
    public          root    false    214   j/                 0    16476    courses_groups 
   TABLE DATA           ?   COPY public.courses_groups (courses_id, groups_id) FROM stdin;
    public          root    false    215   �/       !          0    16480    groups 
   TABLE DATA           ,   COPY public.groups (number, id) FROM stdin;
    public          root    false    217   �/       #          0    16484    subjects 
   TABLE DATA           �   COPY public.subjects (begintime, endtime, numeratorordenominator, subgroup, course_id, group_id, id, info, lecturer, title, weekday) FROM stdin;
    public          root    false    219   
0       %          0    16490    users 
   TABLE DATA           O   COPY public.users (subgroup, course_id, group_id, id, telegram_id) FROM stdin;
    public          root    false    221   �;       1           0    0    courses_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.courses_id_seq', 1, false);
          public          root    false    216            2           0    0    groups_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.groups_id_seq', 1, false);
          public          root    false    218            3           0    0    subjects_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.subjects_id_seq', 2, true);
          public          root    false    220            4           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 22, true);
          public          root    false    222            }           2606    16499    courses courses_number_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_number_key UNIQUE (number);
 D   ALTER TABLE ONLY public.courses DROP CONSTRAINT courses_number_key;
       public            root    false    214                       2606    16501    courses courses_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.courses DROP CONSTRAINT courses_pkey;
       public            root    false    214            �           2606    16503    groups groups_number_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_number_key UNIQUE (number);
 B   ALTER TABLE ONLY public.groups DROP CONSTRAINT groups_number_key;
       public            root    false    217            �           2606    16505    groups groups_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.groups DROP CONSTRAINT groups_pkey;
       public            root    false    217            �           2606    16507    subjects subjects_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.subjects DROP CONSTRAINT subjects_pkey;
       public            root    false    219            �           2606    16509    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            root    false    221            �           2606    16511    users users_telegram_id_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_telegram_id_key UNIQUE (telegram_id);
 E   ALTER TABLE ONLY public.users DROP CONSTRAINT users_telegram_id_key;
       public            root    false    221            �           2606    16512 *   courses_groups fk5agt7oocj3mxonovxgg548w0p    FK CONSTRAINT     �   ALTER TABLE ONLY public.courses_groups
    ADD CONSTRAINT fk5agt7oocj3mxonovxgg548w0p FOREIGN KEY (groups_id) REFERENCES public.groups(id);
 T   ALTER TABLE ONLY public.courses_groups DROP CONSTRAINT fk5agt7oocj3mxonovxgg548w0p;
       public          root    false    215    217    3203            �           2606    16517 !   users fk94cc4mtujq4nljbmtf8ijqf4r    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk94cc4mtujq4nljbmtf8ijqf4r FOREIGN KEY (course_id) REFERENCES public.courses(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fk94cc4mtujq4nljbmtf8ijqf4r;
       public          root    false    214    3199    221            �           2606    16522 $   subjects fk97opnpttu556n9ep4ful6vq2h    FK CONSTRAINT     �   ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT fk97opnpttu556n9ep4ful6vq2h FOREIGN KEY (group_id) REFERENCES public.groups(id);
 N   ALTER TABLE ONLY public.subjects DROP CONSTRAINT fk97opnpttu556n9ep4ful6vq2h;
       public          root    false    217    3203    219            �           2606    16527 !   users fkemfuglprp85bh5xwhfm898ysc    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkemfuglprp85bh5xwhfm898ysc FOREIGN KEY (group_id) REFERENCES public.groups(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fkemfuglprp85bh5xwhfm898ysc;
       public          root    false    221    217    3203            �           2606    16532 *   courses_groups fkk4926qwwxvp6bsm96ela4w3mp    FK CONSTRAINT     �   ALTER TABLE ONLY public.courses_groups
    ADD CONSTRAINT fkk4926qwwxvp6bsm96ela4w3mp FOREIGN KEY (courses_id) REFERENCES public.courses(id);
 T   ALTER TABLE ONLY public.courses_groups DROP CONSTRAINT fkk4926qwwxvp6bsm96ela4w3mp;
       public          root    false    214    215    3199            �           2606    16537 $   subjects fkr4k4crqhj5ojibxp458ndbywv    FK CONSTRAINT     �   ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT fkr4k4crqhj5ojibxp458ndbywv FOREIGN KEY (course_id) REFERENCES public.courses(id);
 N   ALTER TABLE ONLY public.subjects DROP CONSTRAINT fkr4k4crqhj5ojibxp458ndbywv;
       public          root    false    219    3199    214               (   x��A 0��w+fw� 3�cI&iQ^�M;�����D�         '   x�3��2�4\�l$�����PȊ���� ��6      !   !   x����4�4�27�4�F��F\1z\\\ 8O�      #   �  x��\Ko�>�~��$@dM�{xbI +���[N�%���%�	D�2L���$9�C�(���RU��YnWO��	�r����������b-���8�Z��J�:�"�\�4�=|s ��s�F����_��y&O��A��n~r�OW�z-Shb-!4_��G"��3y�|	"���5O"9�(�s��m���PB��Ѻ�}t�p�Ԫ6\s_ع�����j&���ë�Ҭ�oï�X��HB��Ҽ�������M����t-��v+&\� ��0�׈}�Ͽ��omC�k��̍� @𒦑�/��1jDr^��)�������|�|%߷qW�d�-�(M�H~�<h�A�3RL� �r����5� F��"'`�s��L���Fr�`��f��~�|6>m���k��xMh�ɣ,�A'��Z��w�ft�wo����E��y$_@�>����g-y~�U��E$���&Y=���C{ u��zy�7�~ǁ��H�$�I���Rj�]�ɓz@[���L�L$7ɇ&tti�8�C��8�}Z���1F�Z�H��eI2�K�2��\8�?����ܽ�wNR�)�@9�ժ�j�Ej�?��5������E@��C^9�#Y�z���Z�G�P��-�2�\�_�'E��qD	�Oʔv�Yͤ�_䣂DI�q�4��Ap`m�m��^���Ӂ��l��<�K*��bb.Q�굄-KJi@��a�'�d�3N�vs �OZ�`1EA�q��(@<V3,�a�XǙU |(g��]��x��#:����/��>;��݇���/mNZ�jPS/j�E]��U�Z)Sa/���ef�b�V�N+IGs;K�V�$	(�xE9d=,l��b\�������Z��A�� ��&<���T�&A%��s>� ��H��\��ʹ͙+i��A��w@�1�N�O��F��F��� �B��)ZǬjc&���.���߼q���XG�Ô)2ܴ��*�A����ϻ��$����r��=����c�"��9��1+۽��@G�W�
����}^E�5*�����5
��3�;�Y4g�$mj��x)��n��׉B�w��+z�H�]�.KI`=آzu��Rm[�k����s�E(�o'�j���;�"TfQ���(rv��Q\����QQ��[��Ë��
��!�7ȦD!�2����v�f�?Znu��ʺ�.Y[���"�[� .,y�$ �E*�` �����\^�q����&�)z��'��2 ��¨�&=U�ߞ�����b\6��3�֪��{K��S:��W�-��;|]8��e�Hֱ}7 !$�v������ɥä-��2����(	���4��;�8��D�]��w>� <V=04���'��q� e����3d%����<��Fo��{2f�x�vB�6q��F9A ��~ζ��z����5���I���( |��`����S&,�7tf5s��]���%��-���
k�=�Ǌ��,�z�@����ҡ�"U��w�<)qa�o���H�,ߴB�W���3�FI�C�Ӏ+N�xA���a�}
���΢����2G4��J��Z�!��� �W6j�K��p�@�0��l +Ë�r�����9�`8���tvgdA�2ENF�|���HŘ�l��@�$Е�Q��(3#n�/5V��� �'gw��ggDHCҳ�5xw�$8�j��4��\�E���H�8P9"ˢ|5"͢|=*�B$Z�2Tu&�J�d���˹�֣dS�̇%֘x��rtL� k�^ov�B��Pr���	Q7(}M��:�X��
�=��[��/ �CP���M4n�˃Rᦊ� d�F	[�*l��0�.M:��:�"L"��_5���r�؅�.�����{��}Ǣ�N�0�V<晪,t���r͞>��w^"i(*w�q���9��3tM�H�d4c8q�@eT�N��\ 2V����|]�Yuymh�΢t��+o��@)b+�M�v+/��]v4�H���7��.JC���ݮD���;674J> ���(L���/�Z�f�5�2��'�*UH ��4_��\E^@]y��E�������g����͚/��A��*��#����� D��D���e���_�NM��S�p��$B� ����ɢ�Nhg0�#y��{*!��l�I�q��=7{��R���5J^I��X�?�H^ܶl���MgK�Jݍ<'����E� �3P� ��6T���x��&�j�C�*/�*�]�O���"o� 8{��|*[�٘k�&(}H���3F�dm8�reca��y�;��-�\/X��|P-;� u=�ݦv�q�����Q׾R�5GM�ۤ�	��Ѣƪ�O��'��b���OeZ�]�P2`��6F��>2���i6֜1�5R�V��ix�k��?9��=U��R���賟�\�Zت�~l7'!�SՐ�ꄆ���<�e��	�wM�IPm�e#D\�pl��ل�	��v�g��~���-�-GU+m�Vc���(W�<�Wh�E/b��>"J2�^ik�NS���*�,m��	*�6^�_�7���H�?��A�5�����(]%S?>&b�� ��>�8!u�;��&;�q�,S�����R�ql�p�G�|Y 1H�9��S[�>���uzf	���뚵:�c��NU��q]����P���N�Ģ��Ua�d���@K�?��
4�Ī��t��a�倅�Ҵ[�i%��D����9]	���#7��q/n`<E�n�'�+.�pB����y[Q>�{���?���+�����Ԏ�z��p뚊'|���v�X~x���Ynj���G����K������&�,@�y2�����F[a���_R�C��蓶cW]�{(!�/��\C4��㫊����<��F��p�1/Z{8�X�b�g����A�B'�=�*OGA���+W���K��      %   �   x�=����0Cc��� {��ױ��r��!..c���P��7��Fky�E%f^�"�E-j�Z>�b>�-^�0
j��SXkG�\�
f��3�����+�}H�Q�1��AzT���VHm ���lE��򠒙acb>�6�Z���\i�)�g��Eu��G\����s&�8��nv糴o%���[!��:�.�]\����u]��2Ew     