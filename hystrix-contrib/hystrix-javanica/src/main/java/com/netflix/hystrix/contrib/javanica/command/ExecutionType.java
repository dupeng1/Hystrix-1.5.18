/**
 * Copyright 2012 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.hystrix.contrib.javanica.command;

import rx.Observable;

import java.util.concurrent.Future;

/**
 * Specifies executions types.
 */
public enum ExecutionType {

    /**
     * Used for asynchronous execution of command.
     */
    // 异步， 命令的返回值为 Future，可使用 Future#get 方法 阻塞式 获取执行结果
    ASYNCHRONOUS,

    /**
     * Used for synchronous execution of command.
     */
    // 同步
    SYNCHRONOUS,

    /**
     * Reactive execution (asynchronous callback).
     */
    // 响应式（异步回调），可获取【发射执行结果】的 Observable，进行【订阅】，订阅对应的异步回调方法
    OBSERVABLE;

    /**
     * Gets execution type for specified class type.
     * @param type the type
     * @return the execution type {@link ExecutionType}
     */
    /**
     * 命令的类型取决于切点方法的返回值
     * Future -> ASYNCHRONOUS
     * Observable -> OBSERVABLE
     * 其他 -> SYNCHRONOUS
     */
    public static ExecutionType getExecutionType(Class<?> type) {
        if (Future.class.isAssignableFrom(type)) {
            return ExecutionType.ASYNCHRONOUS;
        } else if (Observable.class.isAssignableFrom(type)) {
            return ExecutionType.OBSERVABLE;
        } else {
            return ExecutionType.SYNCHRONOUS;
        }
    }

}
