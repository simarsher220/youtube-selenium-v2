package org.codejudge.sb.serivce.api;

import org.codejudge.sb.entity.Sentiment;
import org.codejudge.sb.model.EvalRequest;

public interface AppService {
    public Sentiment initiate(EvalRequest request);
    public Sentiment getSentiments(Integer id);
}
