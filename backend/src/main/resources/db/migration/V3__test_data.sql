INSERT INTO `uom`
VALUES (_binary '\��\�.�AE��y|A\��', 'Grammi', 'g'),
       (_binary '�V���*DX��\�٬c\�\�', 'Millilitri', 'ml'),
       (_binary '��}UK��\�L�\�*=', 'Quanto basta', 'q.b.'),
       (_binary '\�#-ּeK��\����\��', 'Cucchiaino', 'cucchiaino'),
       (_binary '\�W�a��O��Z��_@', 'Numero', '#'),
       (_binary '\�F3�\�O��o\��\�H\"�', 'Litri', 'l'),
       (_binary '�ǇҴxJ �ۣ��t\�', 'Pizzico', 'pizzico');
INSERT INTO `ingredient`
VALUES (_binary '\0)\�\�\�F��%8Ϡ', 'Lievito in polvere per dolci'),
       (_binary 'a:Hd�맪H�%�', 'Zucchero a velo'),
       (_binary '�\�}.SC��.�P\r\0�d', 'Panna fresca'),
       (_binary 'F��\��B�q�9ƒ�', 'Olio'),
       (_binary '+\�4�\�M6�5�T1!�', 'Confettura albicocche'),
       (_binary '.R��l�C󀡼#Ue+�', 'Noci'),
       (_binary 'A:\�\�x�D��2��\��V', 'Bacello di vaniglia'),
       (_binary 'M~�9�PF1�\Z�\�	\�<\�', 'Cioccolato'),
       (_binary 'NYE �A�����', 'Zucchero'),
       (_binary 's�=s%Lx�����}�)', 'Essenza di vaniglia'),
       (_binary 'yX#���M۹c��~YY', 'Burro'),
       (_binary '�s���D��D\�\�v��T', 'Cioccolato'),
       (_binary '�@�=WlD��Z�ķY', 'Farina 00'),
       (_binary '���\�lD\�[;9�·', 'Carote'),
       (_binary '\�o\�ণC���Z\��s\�', 'Cioccolato fondente'),
       (_binary '\�\�^���A9�\�!\�4IT', 'Sale fino'),
       (_binary '\�2\"��C�o��*�m', 'Scorza d\'arancia'),
       (_binary '\�~6�\�3E���6�+�E8', 'Uova'),
       (_binary '\�\r��	I�C���I�f', 'Latte intero');
INSERT INTO `cake`
VALUES (_binary 'K	8M��s�|n��F', 10.00, 'Torta paradiso'),
       (_binary '��g�WlE��\�&\�{x4', 8.00, 'Cupcake classico'),
       (_binary '\�XkW\�{I]����\��\�', 15.00, 'Sacher');
INSERT INTO `cake_ingredient`
VALUES (_binary '\n�\�\��D+�����\n', 1.00, _binary 'K	8M��s�|n��F', _binary '\�\�^���A9�\�!\�4IT', _binary '�ǇҴxJ �ۣ��t\�'),
       (_binary '�(\�D:�&�MT/\�', 120.00, _binary '��g�WlE��\�&\�{x4', _binary 'yX#���M۹c��~YY', _binary '\��\�.�AE��y|A\��'),
       (_binary ')ǲe=#N\�\�Lr�Ao', 65.00, _binary '\�XkW\�{I]����\��\�', _binary '�@�=WlD��Z�ķY', _binary '\��\�.�AE��y|A\��'),
       (_binary '+�S�KȰ�_dـ`�', 40.00, _binary 'K	8M��s�|n��F', _binary 'NYE �A�����', _binary '\��\�.�AE��y|A\��'),
       (_binary '+���4\�GW�*�Z\\\�', 1.00, _binary '��g�WlE��\�&\�{x4', _binary 's�=s%Lx�����}�)', _binary '\�#-ּeK��\����\��'),
       (_binary '6�#RF�\�\�\�~ZQ>', 3.00, _binary '\�XkW\�{I]����\��\�', _binary '\�~6�\�3E���6�+�E8', _binary '\�W�a��O��Z��_@'),
       (_binary 'LGte6�O�� w�\�', 2.00, _binary '��g�WlE��\�&\�{x4', _binary '\0)\�\�\�F��%8Ϡ', _binary '\��\�.�AE��y|A\��'),
       (_binary 'P;#�C��ג\�.(\r\�', 90.00, _binary '\�XkW\�{I]����\��\�', _binary 'NYE �A�����', _binary '\��\�.�AE��y|A\��'),
       (_binary 'X/\�\�QA���\�D�o', 1.00, _binary '\�XkW\�{I]����\��\�', _binary 'A:\�\�x�D��2��\��V', _binary '\�W�a��O��Z��_@'),
       (_binary '_\�\�EAƯ\�Am��', 120.00, _binary '��g�WlE��\�&\�{x4', _binary 'NYE �A�����', _binary '\��\�.�AE��y|A\��'),
       (_binary 'l�z\�H.@��!}hk/4', 150.00, _binary '\�XkW\�{I]����\��\�', _binary '+\�4�\�M6�5�T1!�', _binary '\��\�.�AE��y|A\��'),
       (_binary 'nl\���C㄂�T\�g\�', 75.00, _binary '\�XkW\�{I]����\��\�', _binary '\�o\�ণC���Z\��s\�', _binary '\��\�.�AE��y|A\��'),
       (_binary '�#�+DJo�\�6�7\n\"', 120.00, _binary '��g�WlE��\�&\�{x4', _binary '�@�=WlD��Z�ķY', _binary '\��\�.�AE��y|A\��'),
       (_binary '�+{5��M�+��j8\n', 300.00, _binary '��g�WlE��\�&\�{x4', _binary 'a:Hd�맪H�%�', _binary '\��\�.�AE��y|A\��'),
       (_binary '�\�;\�\�MJ\�2y!�l', 1.00, _binary 'K	8M��s�|n��F', _binary '\�2\"��C�o��*�m', _binary '��}UK��\�L�\�*='),
       (_binary '����CqCA�{8!\�%l', 3.00, _binary 'K	8M��s�|n��F', _binary '\0)\�\�\�F��%8Ϡ', _binary '\��\�.�AE��y|A\��'),
       (_binary '��]\�8N{�AW*u�\�*', 3.00, _binary '��g�WlE��\�&\�{x4', _binary '\�~6�\�3E���6�+�E8', _binary '\�W�a��O��Z��_@'),
       (_binary '�>cB�J{��`\�dr\�', 65.00, _binary '\�XkW\�{I]����\��\�', _binary 'yX#���M۹c��~YY', _binary '\��\�.�AE��y|A\��'),
       (_binary '\�q�5\n\�Le�z`�.#�\�', 20.00, _binary '\�XkW\�{I]����\��\�', _binary 'a:Hd�맪H�%�', _binary '\��\�.�AE��y|A\��'),
       (_binary 'Ȃf��iI��zO\�D�N[', 170.00, _binary 'K	8M��s�|n��F', _binary 'a:Hd�맪H�%�', _binary '\��\�.�AE��y|A\��'),
       (_binary 'я{�3�A��R�~3#��', 100.00, _binary 'K	8M��s�|n��F', _binary '\�~6�\�3E���6�+�E8', _binary '\��\�.�AE��y|A\��'),
       (_binary '\�{-+rE��ɂ���1', 170.00, _binary 'K	8M��s�|n��F', _binary 'yX#���M۹c��~YY', _binary '\��\�.�AE��y|A\��'),
       (_binary '\�3�qDѼ\�\"\Z��', 1.00, _binary '\�XkW\�{I]����\��\�', _binary '\�\�^���A9�\�!\�4IT', _binary '�ǇҴxJ �ۣ��t\�'),
       (_binary '\�<�\r\�G���X��\�?�', 1.00, _binary '��g�WlE��\�&\�{x4', _binary '\�\�^���A9�\�!\�4IT', _binary '�ǇҴxJ �ۣ��t\�'),
       (_binary '\�\"N�lkHj�^\�0��[\"', 125.00, _binary '\�XkW\�{I]����\��\�', _binary '�\�}.SC��.�P\r\0�d', _binary '\��\�.�AE��y|A\��'),
       (_binary '\�}~�dH)�\�\�J�^�', 100.00, _binary 'K	8M��s�|n��F', _binary '�@�=WlD��Z�ķY', _binary '\��\�.�AE��y|A\��');
INSERT INTO `batch`
VALUES (_binary '2��Z`I)��gCpu]', 20.00, '2021-04-07 19:35:00.000000', 5, _binary '\�XkW\�{I]����\��\�'),
       (_binary 'G6�P:�K�����ʠA', 8.00, '2021-04-08 19:37:00.000000', 22, _binary '��g�WlE��\�&\�{x4'),
       (_binary 'eb�q2GM��Ɓ3J�', 15.00, '2021-04-10 11:00:00.000000', 6, _binary '\�XkW\�{I]����\��\�'),
       (_binary 'h%\�C�A��]\�\�', 15.00, '2021-04-11 12:00:00.000000', 3, _binary '\�XkW\�{I]����\��\�'),
       (_binary 'jG�u\rQMN��\�a\�w\�', 8.00, '2021-04-02 19:36:00.000000', 20, _binary '��g�WlE��\�&\�{x4'),
       (_binary 'wO�0D(���\�N0+\0', 12.00, '2021-04-09 19:37:00.000000', 9, _binary 'K	8M��s�|n��F'),
       (_binary '\�!�\�d\nA��\�\��P&', 5.00, '2021-04-15 19:36:00.000000', 40, _binary '��g�WlE��\�&\�{x4'),
       (_binary 'ߕ\�ѐ�C^��\��(�\Z', 8.00, '2021-04-10 19:36:00.000000', 12, _binary '��g�WlE��\�&\�{x4'),
       (_binary '\�G���\�M��\�2g���', 10.00, '2021-04-13 19:36:00.000000', 7, _binary 'K	8M��s�|n��F');