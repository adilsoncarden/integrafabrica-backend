
CREATE TABLE
    roles (id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL);

CREATE TABLE
    users (
        id UUID PRIMARY KEY,
        username TEXT NOT NULL,
        email TEXT NOT NULL,
        password TEXT NOT NULL,
        role_id INT NOT NULL,
        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (role_id) REFERENCES roles (id)
    );

CREATE TABLE
    categories (
        id UUID PRIMARY KEY,
        name TEXT NOT NULL,
        description TEXT,
        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE
    locations (
        id UUID PRIMARY KEY,
        aisle TEXT NOT NULL,
        rack TEXT NOT NULL,
        level TEXT NOT NULL,
        description TEXT,
        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE
    suppliers (
        id UUID PRIMARY KEY,
        ruc TEXT NOT NULL,
        company_name TEXT NOT NULL,
        contact_name TEXT,
        phone TEXT,
        email TEXT,
        delivery_time_days INT,
        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE
    products (
        id UUID PRIMARY KEY,
        sku TEXT NOT NULL,
        name TEXT NOT NULL,
        category_id UUID NOT NULL,
        location_id UUID,
        unit TEXT NOT NULL,
        stock INT NOT NULL,
        min_stock INT NOT NULL,
        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (category_id) REFERENCES categories (id),
        FOREIGN KEY (location_id) REFERENCES locations (id)
    );

CREATE TABLE
    product_batches (
        id UUID PRIMARY KEY,
        product_id UUID NOT NULL,
        batch_code TEXT NOT NULL,
        expiration_date DATE NOT NULL,
        initial_quantity INT NOT NULL,
        current_quantity INT NOT NULL,
        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (product_id) REFERENCES products (id)
    );

CREATE TABLE
    movements (
        id UUID PRIMARY KEY,
        movement_type TEXT NOT NULL, -- e.g., IN, OUT
        reason TEXT NOT NULL,
        supplier_id UUID,
        reference_document_type TEXT,
        reference_document_number TEXT,
        performed_by UUID NOT NULL,
        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (supplier_id) REFERENCES suppliers (id),
        FOREIGN KEY (performed_by) REFERENCES users (id)
    );

CREATE TABLE
    movement_details (
        id UUID PRIMARY KEY,
        movement_id UUID NOT NULL,
        product_id UUID NOT NULL,
        batch_id UUID,
        quantity INT NOT NULL,
        FOREIGN KEY (movement_id) REFERENCES movements (id),
        FOREIGN KEY (product_id) REFERENCES products (id),
        FOREIGN KEY (batch_id) REFERENCES product_batches (id)
    );

INSERT INTO
    roles (id, name)
VALUES
    (1, 'ADMIN');

INSERT INTO
    users (
        id,
        username,
        email,
        password,
        role_id,
        created_at,
        updated_at
    )
VALUES
    (
        'u1111111-1111-1111-1111-111111111111',
        'admin_integra',
        'admin@integrafabrica.pe',
        '$2a$12$ExampleHashForSecurity Purposes',
        1,
        '2026-07-03 10:00:00-05',
        '2026-07-03 10:00:00-05'
    );

INSERT INTO
    categories (id, name, description, created_at)
VALUES
    (
        'c6666666-6666-6666-6666-666666666666',
        'EPIs y Seguridad',
        'Cascos, guantes de nitrilo, lentes con protección UV y botas dieléctricas',
        '2026-07-03 10:00:00-05'
    ),
    (
        'c7777777-7777-7777-7777-777777777777',
        'Químicos y Lubricantes',
        'Fluidos refrigerantes, grasas industriales y solventes de limpieza de maquinaria',
        '2026-07-03 10:05:00-05'
    ),
    (
        'c8888888-8888-8888-8888-888888888888',
        'Suministros de Limpieza',
        'Insumos para el mantenimiento sanitario de las oficinas y naves de producción',
        '2026-07-03 10:10:00-05'
    ),
    (
        'c9999999-9999-9999-9999-999999999999',
        'Repuestos de Maquinaria',
        'Piezas de recambio mecánico, fajas de transmisión, rodajes y neumáticos',
        '2026-07-03 10:15:00-05'
    ),
    (
        'caaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'Ferretería General',
        'Pernos, tuercas, arandelas, clavos y consumibles menores de ensamble',
        '2026-07-03 10:20:00-05'
    );

INSERT INTO
    locations (id, aisle, rack, level, description, created_at)
VALUES
    (
        'l6666666-6666-6666-6666-666666666666',
        'Pasillo F',
        'Rack F1',
        'Nivel 1',
        'Zona de resguardo climatizado para materiales sensibles',
        '2026-07-03 10:30:00-05'
    ),
    (
        'l7777777-7777-7777-7777-777777777777',
        'Pasillo D',
        'Rack D2',
        'Nivel 1',
        'Estantería reforzada para fluidos pesados',
        '2026-07-03 10:32:00-05'
    ),
    (
        'l8888888-8888-8888-8888-888888888888',
        'Pasillo H',
        'Rack H1',
        'Nivel 4',
        'Custodia de suministros de baja rotación en altura',
        '2026-07-03 10:34:00-05'
    ),
    (
        'l9999999-9999-9999-9999-999999999999',
        'Pasillo F',
        'Rack F2',
        'Nivel 3',
        'Nivel intermedio para repuestos mecánicos medianos',
        '2026-07-03 10:36:00-05'
    ),
    (
        'laaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'Pasillo E',
        'Rack E1',
        'Nivel 1',
        'Módulo de picking rápido para pernería y tornillos',
        '2026-07-03 10:38:00-05'
    );

INSERT INTO
    suppliers (
        id,
        ruc,
        company_name,
        contact_name,
        phone,
        email,
        delivery_time_days,
        created_at
    )
VALUES
    (
        's6666666-6666-6666-6666-666666666666',
        '20998877665',
        'Seguridad Total EPI S.A.C.',
        'Elena Vega',
        '922334455',
        'evega@seguridadtotal.pe',
        3,
        '2026-07-03 10:45:00-05'
    ),
    (
        's7777777-7777-7777-7777-777777777777',
        '20776655443',
        'Suministros Médicos y Limpieza',
        'Pedro Rojas',
        '966778899',
        'projas@mediclean.com.pe',
        2,
        '2026-07-03 10:47:00-05'
    ),
    (
        's8888888-8888-8888-8888-888888888888',
        '20665544332',
        'Metales Continentales S.A.',
        'Gabriel Soto',
        '911223344',
        'gsoto@metalescont.com',
        6,
        '2026-07-03 10:49:00-05'
    ),
    (
        's9999999-9999-9999-9999-999999999999',
        '20223344556',
        'Cables y Energía del Perú',
        'Marta Flores',
        '999888777',
        'mflores@cablesenergia.pe',
        8,
        '2026-07-03 10:51:00-05'
    ),
    (
        'saaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        '20445566778',
        'Distribuidora Industrial S.A.C.',
        'Carlos Mendoza',
        '944332211',
        'cmendoza@distindustrial.pe',
        4,
        '2026-07-03 10:53:00-05'
    );

INSERT INTO
    products (
        id,
        sku,
        name,
        category_id,
        location_id,
        unit,
        stock,
        min_stock,
        created_at,
        updated_at
    )
VALUES
    (
        'p6666666-6666-6666-6666-666666666666',
        'SKU-EP-006',
        'Guantes de Nitrilo - Caja x100',
        'c6666666-6666-6666-6666-666666666666',
        'l6666666-6666-6666-6666-666666666666',
        'CAJA',
        80,
        15,
        '2026-07-03 11:00:00-05',
        '2026-07-03 11:00:00-05'
    ),
    (
        'p7777777-7777-7777-7777-777777777777',
        'SKU-QU-007',
        'Aceite Hidráulico ISO 68',
        'c7777777-7777-7777-7777-777777777777',
        'l7777777-7777-7777-7777-777777777777',
        'GALON',
        30,
        8,
        '2026-07-03 11:02:00-05',
        '2026-07-03 11:02:00-05'
    ),
    (
        'p8888888-8888-8888-8888-888888888888',
        'SKU-LI-008',
        'Detergente Industrial Multiusos',
        'c8888888-8888-8888-8888-888888888888',
        'l8888888-8888-8888-8888-888888888888',
        'TAMBOR',
        25,
        5,
        '2026-07-03 11:04:00-05',
        '2026-07-03 11:04:00-05'
    ),
    (
        'p9999999-9999-9999-9999-999999999999',
        'SKU-RE-009',
        'Rodamiento de Bolas SKF 6204',
        'c9999999-9999-9999-9999-999999999999',
        'l9999999-9999-9999-9999-999999999999',
        'UNIDAD',
        60,
        12,
        '2026-07-03 11:06:00-05',
        '2026-07-03 11:06:00-05'
    ),
    (
        'paaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'SKU-FE-010',
        'Pernos de Anclaje 3/8 x 3',
        'caaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'laaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'CIENTO',
        5,
        1,
        '2026-07-03 11:08:00-05',
        '2026-07-03 11:08:00-05'
    );

INSERT INTO
    product_batches (
        id,
        product_id,
        batch_code,
        expiration_date,
        initial_quantity,
        current_quantity,
        created_at
    )
VALUES
    (
        'b6666666-6666-6666-6666-666666666666',
        'p6666666-6666-6666-6666-666666666666',
        'LOTE-EPI-04',
        '2026-11-07',
        80,
        80,
        '2026-07-03 11:20:00-05'
    ),
    (
        'b7777777-7777-7777-7777-777777777777',
        'p7777777-7777-7777-7777-777777777777',
        'LOTE-QU-88',
        '2028-03-12',
        30,
        30,
        '2026-07-03 11:22:00-05'
    ),
    (
        'b8888888-8888-8888-8888-888888888888',
        'p8888888-8888-8888-8888-888888888888',
        'LOTE-LIM-01',
        '2027-02-28',
        25,
        25,
        '2026-07-03 11:24:00-05'
    ),
    (
        'b9999999-9999-9999-9999-999999999999',
        'p9999999-9999-9999-9999-999999999999',
        'LOTE-REP-34',
        '2036-04-10',
        60,
        60,
        '2026-07-03 11:26:00-05'
    ),
    (
        'baaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'paaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'LOTE-FER-77',
        '2033-11-05',
        5,
        5,
        '2026-07-03 11:28:00-05'
    );

INSERT INTO
    movements (
        id,
        movement_type,
        reason,
        supplier_id,
        reference_document_type,
        reference_document_number,
        performed_by,
        created_at
    )
VALUES
    (
        'm6666666-6666-6666-6666-666666666666',
        'OUT',
        'Desecho por control de calidad obsoleto',
        NULL,
        'INFORME_TECNICO',
        'INF-004-2026',
        'u1111111-1111-1111-1111-111111111111',
        '2026-07-03 11:40:00-05'
    ),
    (
        'm7777777-7777-7777-7777-777777777777',
        'IN',
        'Ingreso de lote de resinas',
        's8888888-8888-8888-8888-888888888888',
        'GUIA_REMISION',
        'G001-001420',
        'u1111111-1111-1111-1111-111111111111',
        '2026-07-03 11:42:00-05'
    ),
    (
        'm8888888-8888-8888-8888-888888888888',
        'OUT',
        'Despacho a línea de producción 2',
        NULL,
        'VALE_CONSUMO',
        'V-9942',
        'u1111111-1111-1111-1111-111111111111',
        '2026-07-03 11:44:00-05'
    ),
    (
        'm9999999-9999-9999-9999-999999999999',
        'IN',
        'Compra de ferretería general',
        'saaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'FACTURA',
        'F004-000215',
        'u1111111-1111-1111-1111-111111111111',
        '2026-07-03 11:46:00-05'
    ),
    (
        'maaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'IN',
        'Devolución de producción por exceso',
        NULL,
        'NOTA_CREDITO_INTERNA',
        'NCI-0012',
        'u1111111-1111-1111-1111-111111111111',
        '2026-07-03 11:48:00-05'
    );
    
INSERT INTO
    movement_details (id, movement_id, product_id, batch_id, quantity)
VALUES
    (
        'md666666-6666-6666-6666-666666666666',
        'm6666666-6666-6666-6666-666666666666',
        'p7777777-7777-7777-7777-777777777777',
        'b7777777-7777-7777-7777-777777777777',
        2
    ),
    (
        'md777777-7777-7777-7777-777777777777',
        'm7777777-7777-7777-7777-777777777777',
        'p9999999-9999-9999-9999-999999999999',
        'b9999999-9999-9999-9999-999999999999',
        50
    ),
    (
        'md888888-8888-8888-8888-888888888888',
        'm8888888-8888-8888-8888-888888888888',
        'p6666666-6666-6666-6666-666666666666',
        'b6666666-6666-6666-6666-666666666666',
        10
    ),
    (
        'md999999-9999-9999-9999-999999999999',
        'm9999999-9999-9999-9999-999999999999',
        'paaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'baaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        100
    ),
    (
        'mdaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'maaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        'p8888888-8888-8888-8888-888888888888',
        'b8888888-8888-8888-8888-888888888888',
        5
    );