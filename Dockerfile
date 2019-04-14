FROM php:7.0-apache

RUN apt-get update && apt-get install -y --no-install-recommends \
    locales \
    zlib1g-dev \
    libpng-dev \
    && rm -rf /var/lib/apt/lists/*

RUN docker-php-ext-install mysqli \
    && docker-php-ext-install mbstring \
    && docker-php-ext-install zip \
    && docker-php-ext-install json \
    && docker-php-ext-install gd \
    && docker-php-ext-install gettext

RUN a2enmod rewrite

RUN echo $'en_US.UTF-8 UTF-8\n\
fr_FR.UTF-8 UTF-8\n\
en_GB.UTF-8 UTF-8\n\
fi_FI.UTF-8 UTF-8\n\
sv_SE.UTF-8 UTF-8\n'\
>> /etc/locale.gen

RUN locale-gen

RUN echo "session.gc_maxlifetime=60000" >> /usr/local/etc/php/php.ini \
    && echo "max_execution_time=3000" >> /usr/local/etc/php/php.ini

RUN mkdir -p /var/www/html/gui/templates_c \
    && mkdir -p /var/testlink/logs/ \
    && mkdir -p /var/testlink/upload_area/ \
    && chown -R www-data: /var/www/html/gui/templates_c \
    && chown -R www-data: /var/testlink/
