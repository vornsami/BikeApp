import React, { useEffect, useState }  from 'react'

import Box from '@material-ui/core/Box'
import { useParams } from 'react-router-dom'

import getPaths from '../services/network'
import PathList from './PathList'
import FailPage from './FailPage'

const MainPage = () => {
  let props = useParams();
  const page = (props.page)? props.page : 1
  const pageLength = 50  

  const [paths, setPaths] = useState([])
  var sortBy = 'Distance'
  const setSort = newSort => {
    sortBy = newSort
  }

  useEffect(() => {
    async function fetchData() {
      const offset = page * pageLength - pageLength
      const initialPaths = await getPaths(sortBy, pageLength, offset)
      setPaths(initialPaths)
    }
    try {
      fetchData()
    } catch (e) {
      console.error(e)
    }
  }, [sortBy, page, pageLength])

  try {
    return <Box>
        {paths !== undefined && <PathList paths={paths} setSort={setSort} sortBy={sortBy}/>}
      </Box>
  } catch (e) {
    return <FailPage/>
  }
}

export default MainPage