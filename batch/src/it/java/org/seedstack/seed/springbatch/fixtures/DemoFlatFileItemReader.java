/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.seed.springbatch.fixtures;

import org.springframework.batch.item.file.FlatFileItemReader;

public class DemoFlatFileItemReader extends FlatFileItemReader<Employee> {


    public DemoFlatFileItemReader() {
        String[] comments = new String[]{"#"};
        setComments(comments);

//		setEncoding("ezrzerez");

//		setLineMapper(lineMapper);

    }

}
